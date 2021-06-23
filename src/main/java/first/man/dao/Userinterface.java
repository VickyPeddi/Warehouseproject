package first.man.dao;


import first.man.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Userinterface extends JpaRepository<User, Integer> {

    //for purchase order
    @Query("select userid,usertype from User where usertype='vendor'")
    List<Object[]> getuseridandusercode();

    //for sale order
    @Query("select userid,usertype from User  where usertype ='customer'")
    List<Object[]> getuseridandusertype();
}
