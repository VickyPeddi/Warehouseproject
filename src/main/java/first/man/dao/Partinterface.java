package first.man.dao;

import first.man.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Partinterface extends JpaRepository <Part,Integer>
{

}
