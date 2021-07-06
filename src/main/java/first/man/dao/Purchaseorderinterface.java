package first.man.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import first.man.model.Purchaseorder;

@Repository
public interface Purchaseorderinterface extends JpaRepository<Purchaseorder, Integer> {

    @Query("select id,ordercode from Purchaseorder ")
    public List<Object[]> getpurchaseidandordercode();
//for non select operation we have to use non select operation
    @Modifying
    @Query("update Purchaseorder  set defaultstatus=:status where  id=:id")
    public void updatepurchaseorderstatus(@Param("status") String status, @Param("id") int id);
    @Query("select p.id,p.ordercode from Purchaseorder p where p.defaultstatus=:status")
    public List<Object[]> getPurchaseorderidandcodebyStatus(@Param("status") String defaultstaus);

}
