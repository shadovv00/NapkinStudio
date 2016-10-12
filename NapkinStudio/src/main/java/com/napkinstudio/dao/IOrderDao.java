package com.napkinstudio.dao;

import com.napkinstudio.entity.Order;

import com.napkinstudio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Repository
public interface IOrderDao extends JpaRepository<Order,Integer> {
//    public List<Status> findByRoleId(@Param("id")Integer id);

public LinkedList<Order> getUpdatedOrders(@Param("lastModifiedDate")Date lastModifiedDate);


}
