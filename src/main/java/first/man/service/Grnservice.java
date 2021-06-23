package first.man.service;

import first.man.dao.Grnrepos;
import first.man.model.Grn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Grnservice {
    @Autowired
    private Grnrepos repos;

    public void deletegrn(int id) {
        repos.deleteById(id);
    }

    public Grn savegrn(Grn grn) {
        return repos.save(grn);

    }

    public Grn getgrn(int id) {
        return repos.findById(id).get();

    }

    public List<Grn> getall() {
        List<Grn> all = repos.findAll();
        if (all == null) {
            return new ArrayList<>();
        } else {
            return all;
        }

    }
}
