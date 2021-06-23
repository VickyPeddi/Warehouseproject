package first.man.dao;

import first.man.model.Ordermethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Orderinterface  extends JpaRepository<Ordermethod,Integer>
{
    @Query("select ordermode,count (ordermode) from Ordermethod group by ordermode")
    List<Object[]> getordercodecount();

}
