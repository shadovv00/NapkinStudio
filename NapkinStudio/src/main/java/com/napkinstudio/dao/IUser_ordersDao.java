package com.napkinstudio.dao;

        import com.napkinstudio.entity.User_orders;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface IUser_ordersDao extends JpaRepository<User_orders,Integer> {

}
