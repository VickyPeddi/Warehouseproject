package first.man.service;


import first.man.dao.Userinterface;
import first.man.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class Userservice {
    @Autowired
    private Userinterface repos;

    public List<User> getall() {
        return repos.findAll();
    }

    public User finduserbyuserid(int id) {
        return repos.findById(id).get();
    }

    public User saveuser(User user) {
        return repos.save(user);
    }

    public void deleteuserbyuserid(int id) {
        repos.deleteById(id);
    }

    //for purchase order
    public Map<Integer, String> getuseridandusercode() {
        List<Object[]> list = repos.getuseridandusercode();
        Map<Integer, String> map = repos.getuseridandusercode().stream()
                .collect(Collectors.toMap(arr -> Integer.valueOf(arr[0].toString()), arr -> arr[1].toString()));
        return map;
//        Map<Integer, String> collect = list.stream().filter(value -> Boolean.parseBoolean(value != null ? "notnull value came" : "null value came")).collect(Collectors.toMap(arr -> Integer.valueOf(arr[0].toString()), arr -> arr[1].toString()));

    }

    //for sale order
    public Map<Integer, String> getuseridandusertype() {
        List<Object[]> saleorder = repos.getuseridandusertype();
        Map<Integer, String> collect = saleorder.stream().collect(Collectors.toMap(arr -> Integer.valueOf(arr[0].toString()), arr -> arr[1].toString()));
        return collect;
    }


}
