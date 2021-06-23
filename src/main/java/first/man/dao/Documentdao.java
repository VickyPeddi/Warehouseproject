package first.man.dao;

import first.man.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Documentdao extends JpaRepository<Document,Integer>
{

    @Query("select docid,docname from Document")
    List<Object[]> findDocumentByDocidAndDocname();
}
