package com.napkinstudio.dao;

import com.napkinstudio.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICommentsDao extends JpaRepository<Comments,Integer> {
    List<Comments> findCommentsbyOrderId(@Param("id") Integer id);

    Integer countAllUnreadComments(@Param("userId")Integer userId, @Param("orderId")Integer orderId);

    Integer countUnreadCommentsByRoleId(@Param("userId")Integer userId, @Param("orderId")Integer orderId,@Param("roleId") Integer role1Id);

    List<Comments> findCommentsByOrderAndRoleId(@Param("orderId") Integer orderId, @Param("roleId") Integer roleId);

    List<Comments> findCommentsbyOrderAndRoleIDs(@Param("orderId")Integer orderId, @Param("roleIdList") List<Integer> roleIdList);

    Integer countUnreadCommentsByRoleIds(@Param("userId") Integer userId,@Param("orderId") Integer orderId,@Param("roleIdList") List<Integer> roleIdList);



}