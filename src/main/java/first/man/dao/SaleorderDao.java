package first.man.dao;

import first.man.model.Saleorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleorderDao  extends JpaRepository<Saleorder, Integer>
{

}
