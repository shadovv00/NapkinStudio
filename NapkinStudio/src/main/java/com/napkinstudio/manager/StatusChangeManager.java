package com.napkinstudio.manager;

import com.napkinstudio.dao.IStatusChangeDao;
import com.napkinstudio.entity.StatusChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class StatusChangeManager {

@Autowired
private IStatusChangeDao statusChangeDao;

    @Transactional
    public void save(StatusChange statusChange) {
        statusChangeDao.save(statusChange);
    }

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
