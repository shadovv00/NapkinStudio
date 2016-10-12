package com.napkinstudio.dao;

import com.napkinstudio.entity.SAPstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISAPstatusDao extends JpaRepository<SAPstatus,Integer> {
//    public List<Status> findByRoleId(@Param("id")Integer id);

}
