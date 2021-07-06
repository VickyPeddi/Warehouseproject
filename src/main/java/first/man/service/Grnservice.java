package first.man.service;

import first.man.dao.Grndtlsrepos;
import first.man.dao.Grnrepos;
import first.man.model.Grn;
import first.man.model.Grndtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class Grnservice {
    @Autowired
    private Grnrepos repos;
    @Autowired
    private Grndtlsrepos grndtlsrepos;

    public void deletegrn(int id) {
        repos.deleteById(id);
    }

    public Integer savegrn(Grn grn) {
        return repos.save(grn).getGrnid();

    }

    public Optional<Grn> getgrn(int id) {
        return repos.findById(id);

    }

    public List<Grn> getall() {
        List<Grn> all = repos.findAll();
        if (all == null) {
            return new ArrayList<>();
        } else {
            return all;
        }

    }
    //screen 2 methods

    public Integer savegrndtls(Grndtl grndtl) {
        return grndtlsrepos.save(grndtl).getId();
    }

}
