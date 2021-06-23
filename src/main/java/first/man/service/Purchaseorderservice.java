package first.man.service;

import first.man.dao.Purchaseorderinterface;
import first.man.exception.Notfoundexception;
import first.man.model.Purchaseorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class Purchaseorderservice {
    @Autowired
    private Purchaseorderinterface repos;


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
        Map<Integer, String> purch = purchase.stream().collect(Collectors.toMap(one -> Integer.valueOf(one[0].toString()), two -> two[1].toString()));
        return purch;

   }

}
