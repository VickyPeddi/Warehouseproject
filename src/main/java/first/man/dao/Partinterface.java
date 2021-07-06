package first.man.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import first.man.model.Part;

@Repository
public interface Partinterface extends JpaRepository <Part,Integer>
{
	@Query("select p.partid,p.partcode from Part p")
	List<Object[]> findpartidandpartcode();
}
