package com.napkinstudio.dao;

import com.napkinstudio.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User1 on 29.07.2016.
 */
@Repository
public interface IStatusDao extends JpaRepository<Status,Integer> {
    public List<Status> findByRoleId(@Param("id")Integer id);



}
