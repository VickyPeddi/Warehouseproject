package first.man.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import first.man.dao.Partinterface;
import first.man.exception.Notfoundexception;
import first.man.model.Part;

@Service
@Transactional
public class Partservice {
    @Autowired
    private Partinterface repos;

    public Part savepart(Part part) {
        if (part == null) {
            throw new Notfoundexception("Part not found exception");
        }
        return repos.save(part);
    }

    public List<Part> getallparts() {
        List<Part> parts = repos.findAll();
        if (parts == null) {
            throw new Notfoundexception("list of Parts not found ");

        }
        return parts;
    }

    public Part getpartbypartid(int id) {
        Part part = repos.findById(id).get();
        if (part == null) {
            throw new Notfoundexception("Part not found of id " + id);
        }
        return part;
    }

    public void deletebyid(int id) {
        repos.deleteById(id);

    }

    public Map<Integer, String> getpartidandpartcode() {
        Map<Integer, String> map = repos.findpartidandpartcode().stream().collect(Collectors.toMap(one -> Integer.valueOf(one[0].toString()), two -> two[1].toString()));
        return map;
    }

}
