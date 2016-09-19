package com.napkinstudio.dao;

import com.napkinstudio.entity.StatusSAPStatusRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by User1 on 26.08.2016.
 */
@Repository
public interface IStatusSAPStatusRole extends JpaRepository<StatusSAPStatusRole,Integer> {

    public StatusSAPStatusRole findStatusByRoleIdAndSAPStatusId(@Param("roleId") Integer roleId,@Param("sapStatusId") Integer sapStatusId);
}
