package com.napkinstudio.manager;

import com.napkinstudio.dao.ISAPstatusDao;
import com.napkinstudio.entity.SAPstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SAPstatusManager {

@Autowired
private ISAPstatusDao sapStatusDao;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SAPstatus findById(Integer id) {
        return sapStatusDao.findOne(id);
    }


//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
