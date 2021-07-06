package first.man.service;

import first.man.dao.Purchasedtlsreops;
import first.man.dao.Purchaseorderinterface;
import first.man.exception.Notfoundexception;
import first.man.model.Purchasedtl;
import first.man.model.Purchaseorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.management.ManagementPermission;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class Purchaseorderservice {
    @Autowired
    private Purchaseorderinterface repos;

    @Autowired
    private Purchasedtlsreops purchasedtlsreops;

    public Purchaseorder savepurchaseorder(Purchaseorder purchaseorder) {
        if (purchaseorder == null) {
            throw new Notfoundexception("purhase order unable to save because of its null");
        }
        return repos.save(purchaseorder);
    }

    public Purchaseorder getpurchaseorderbyid(int id) {
        Purchaseorder purchaseorder = repos.findById(id).get();
        if (purchaseorder == null) {
            throw new Notfoundexception("given purchase order id not found");

        }
        return purchaseorder;
    }

    public void deletepurchaseorderbyid(int id) {
        repos.deleteById(id);
    }

    public List<Purchaseorder> getallpurchaseorders() {
        List<Purchaseorder> all = repos.findAll();
//
//        if (all.isEmpty()) {
//            throw new Notfoundexception("purchase orders are null ");
//        }
        return all;
    }

    public Map<Integer, String> getpurchaseorderandordercode() {

        List<Object[]> purchase = repos.getpurchaseidandordercode();
        Map<Integer, String> purch = purchase.stream()
                .collect(Collectors.toMap(one -> Integer.valueOf(one[0].toString()), two -> two[1].toString()));
        return purch;

    }

    public Integer addparttopo(Purchasedtl purchasedtl) {
        return purchasedtlsreops.save(purchasedtl).getId();
    }

    public List<Purchasedtl> findbypurchaseorderid(int id) {
        return purchasedtlsreops.findByPurchaseorder_Id(id);
    }

    public void deletepurchasedtls(int id) {
        purchasedtlsreops.deleteById(id);
    }

    public void updatepurchaseorderstatus(String status, int id) {
        repos.updatepurchaseorderstatus(status, id);
    }

    public int PurchasedtlcountByPurchaseorder(int purchaseorderid) {
        return purchasedtlsreops.PurchasedtlcountByPurchaseorder(purchaseorderid);

    }

    public Map<Integer, String> getPurchaseorderidandcodebyStatus(String defaultstatus) {
        Map<Integer, String> collect = repos.getPurchaseorderidandcodebyStatus(defaultstatus).stream()
                .collect(Collectors.toMap(man -> Integer.valueOf(man[0].toString()), man -> man[1].toString()));

        return collect;
    }

}
