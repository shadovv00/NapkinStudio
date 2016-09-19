package com.napkinstudio.dao;

import com.napkinstudio.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICommentsDao extends JpaRepository<Comments,Integer> {
    List<Comments> findCommentsbyOrderId(@Param("id") Integer id);
//    public List<Status> findByRoleId(@Param("id")Integer id);

}