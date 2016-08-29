package com.napkinstudio.manager;

import com.napkinstudio.dao.ISAPstatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SAPstatusManager {

@Autowired
private ISAPstatusDao sapStatusDao;

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
