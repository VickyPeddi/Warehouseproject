package first.man.dao;

import first.man.model.Grn;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Grnrepos  extends JpaRepository<Grn,Integer>
{


}
