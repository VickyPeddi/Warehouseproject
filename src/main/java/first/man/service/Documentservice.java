package first.man.service;


import first.man.dao.Documentdao;
import first.man.exception.Notfoundexception;
import first.man.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Documentservice {

    @Autowired
    private Documentdao repos;

    public Document savedocument(Document document) {
        if (document == null) {
            throw new Notfoundexception("document not found to save");
        }
        return repos.save(document);

    }

    public Document getdocumentbyid(int id) {
        return repos.findById(id).get();
    }

    public void deletedocument(int id) {
        repos.deleteById(id);
    }

    public List<Document> getlall() {
        return repos.findAll();
    }
    public List<Object[]> finddocidandname(){
        return repos.findDocumentByDocidAndDocname();
    }
}
