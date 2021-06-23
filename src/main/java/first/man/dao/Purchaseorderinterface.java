package first.man.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import first.man.model.Purchaseorder;

import java.util.List;

@Repository
public interface Purchaseorderinterface extends JpaRepository<Purchaseorder, Integer> {

    @Query("select id,ordercode from Purchaseorder ")
    public List<Object[]> getpurchaseidandordercode();

}
