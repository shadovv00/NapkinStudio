package com.napkinstudio.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.napkinstudio.dao.IRoleDao;
import com.napkinstudio.entity.Role;

/**
 * Created by User1 on 02.08.2016.
 */
@Service
public class RoleManager {

    @Autowired
    private IRoleDao roleDao;

    @Transactional
    public void save(Role role) {
        roleDao.save(role);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Role findById(Integer id) {
        return roleDao.findOne(id);
    }

    @Transactional
    public Role findByUserId(Integer id) {
        return roleDao.findByUserId(id);
    }
    
    
//    public List<Role> findCommentsByOrderId() {
//        return roleDao.findCommentsByOrderId();
//    }
}
