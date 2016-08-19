package com.napkinstudio.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.napkinstudio.entity.PersistentLogin;
import com.napkinstudio.entity.User;

@Repository
public interface IPersistentLoginDao extends JpaRepository<PersistentLogin,String>{
    public PersistentLogin findByUserName(@Param("username")String username);


}
