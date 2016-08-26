package com.napkinstudio.manager;

import com.napkinstudio.dao.IStatusDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by User1 on 02.08.2016.
 */

@Service
public class StatusManager {

@Autowired
private IStatusDao statusDao;

//    @Transactional
//    public List<Status> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
