package first.man.service;

import first.man.dao.SaleorderDao;
import first.man.model.Saleorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Saleorderservice {
    @Autowired
    private SaleorderDao repos;

    public Saleorder savesaleorder(Saleorder saleorder) {
        Saleorder save = repos.save(saleorder);
        return save;
    }

    public Saleorder getonesaleorder(int id) {
        Saleorder saleorder = repos.findById(id).get();
        return saleorder;
    }

    public void deleteonesaleorder(int id) {
        repos.deleteById(id);

    }

    public List<Saleorder> findall() {
        return repos.findAll();
    }
}
