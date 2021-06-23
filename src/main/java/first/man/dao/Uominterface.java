package first.man.dao;

import first.man.model.Uommodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Uominterface extends JpaRepository<Uommodel,Integer>
{
    @Query("select uomid,uommodel from Uommodel")
    List<Object[]> getuommodelanduomid();
}
