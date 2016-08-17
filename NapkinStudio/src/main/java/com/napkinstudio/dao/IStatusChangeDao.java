package com.napkinstudio.dao;

import com.napkinstudio.entity.StatusChange;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IStatusChangeDao extends JpaRepository<StatusChange,Integer> {
//    public List<Status> findByRoleId(@Param("id")Integer id);

}
