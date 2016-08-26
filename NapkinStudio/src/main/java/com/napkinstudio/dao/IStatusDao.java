package com.napkinstudio.dao;

import com.napkinstudio.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User1 on 29.07.2016.
 */
@Repository
public interface IStatusDao extends JpaRepository<Status,Integer> {


//    public List<Status> findByRoleId(@Param("id")Integer id);
//
//
//    public  List<Status> findByRoleAndSAPStatusId(@Param("roleId")Integer roleId,@Param("sapStatusId")Integer sapStatusId);
}
