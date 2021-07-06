package first.man.dao;

import first.man.model.Purchasedtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Purchasedtlsreops extends JpaRepository<Purchasedtl, Integer> {
    public List<Purchasedtl> findByPurchaseorder_Id(int id);
    @Query("select count(pdtl.id) from Purchasedtl  pdtl inner join pdtl.purchaseorder as po where po.id =:purchaseid")
    public Integer PurchasedtlcountByPurchaseorder(@Param("purchaseid") int purchaseid);

}
