package com.napkinstudio.manager;


import com.napkinstudio.dao.ISynchronizationDateDao;
import com.napkinstudio.entity.SynchronizationDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SynchronizationDateManager {
    @Autowired
    private ISynchronizationDateDao synchro_dateDao;
    @Transactional
    public void save(SynchronizationDate synchro_date) {
        synchro_dateDao.save(synchro_date);
    }

}
