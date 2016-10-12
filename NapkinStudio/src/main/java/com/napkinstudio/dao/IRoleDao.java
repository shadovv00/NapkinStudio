package com.napkinstudio.dao;

import com.napkinstudio.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by User1 on 20.07.2016.
 */
@Repository
public interface IRoleDao extends JpaRepository<Role,Integer> {

//    public List<Role> findCommentsByOrderId();

    public Role findByUserId(@Param("id")Integer id);

}
