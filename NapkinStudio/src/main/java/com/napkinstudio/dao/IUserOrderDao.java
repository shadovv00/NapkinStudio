package com.napkinstudio.dao;

import com.napkinstudio.entity.User;
import com.napkinstudio.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserOrderDao extends JpaRepository<UserOrder,Integer> {

        List<UserOrder> findOrdersByUserId(@Param("id") Integer userId);

    List<UserOrder> findUserOrdersByOrderId(@Param("id") Integer orderId);

    User findUserforOrdedByRole(@Param("orderId") Integer orderId, @Param("roleId") Integer roleId);
}
