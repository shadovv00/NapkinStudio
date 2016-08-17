package com.napkinstudio.dao;

import com.napkinstudio.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ICustomerDao extends JpaRepository<Customer,Integer> {
//    public List<Status> findByRoleId(@Param("id")Integer id);

}
