package com.napkinstudio.manager;

import com.napkinstudio.dao.IStatusSAPStatusRole;
import com.napkinstudio.entity.StatusSAPStatusRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User1 on 29.08.2016.
 */
@Service
public class StatusSAPStatusRoleManager {

    @Autowired
   private IStatusSAPStatusRole statusSAPStatusRoleDao ;

    public StatusSAPStatusRole findStatusByRoleIdAndSAPStatusId(Integer roleId, Integer SAPStatusId) {
        return statusSAPStatusRoleDao.findStatusByRoleIdAndSAPStatusId(roleId,SAPStatusId );
    }
}
