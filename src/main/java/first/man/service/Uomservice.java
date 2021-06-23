package first.man.service;

import first.man.dao.Uominterface;
import first.man.exception.Notfoundexception;
import first.man.model.Uommodel;
import javassist.bytecode.stackmap.MapMaker;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional

public class Uomservice {
    @Autowired
    private Uominterface repos;

    public void deleteuombyid(int id) {
        repos.deleteById(id);
    }

    public Uommodel save(Uommodel uommodel) {
        if (uommodel == null) {
            throw new Notfoundexception("uom details not found so unable to save");


        }
        return repos.save(uommodel);
    }

    public Uommodel getuombyid(int id) {
        Uommodel uommodel = repos.findById(id).get();
        if (uommodel == null) {
            throw new Notfoundexception("no uom found of this user id");

        }
        return uommodel;
    }

    public List<Uommodel> getall() {

        return repos.findAll();
    }

    public Map<Integer, String> getuomidandmodel() {

//        Map<Integer, String> map = repos.getuommodelanduomid().stream().collect(Collectors.toMap(arr -> Integer.valueOf(arr[0].toString()), arr -> arr[1].toString()));
//        return map;
        List<Object[]> list = repos.getuommodelanduomid();
        Map<Integer,String> map = new LinkedHashMap<>();
        for(Object[]obj:list){
            map.put(Integer.parseInt(obj[0].toString()),obj[1].toString());
        }
        return map;

    }

    public Page<Uommodel> getpagination(Pageable pageable)
    {
        return repos.findAll(pageable);
    }
}
