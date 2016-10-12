package com.napkinstudio.manager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.napkinstudio.dao.IProgresBarFieldsDao;
import com.napkinstudio.entity.ProgresBarFields;

@Service
public class ProgresBarFieldsManager {

    @Autowired
    private IProgresBarFieldsDao    progresBarFieldsDao;

    @Transactional
    public void save(ProgresBarFields progresBarFields) {
        progresBarFieldsDao.save(progresBarFields);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<ProgresBarFields> findAll() {
        return progresBarFieldsDao.findAll();
    }

    public  List<Object[]> findBarByRolePVICheckReject(Integer orderId, Integer roleId, Boolean pVIcheckScen, Boolean rejected, Boolean repeated) {
        return progresBarFieldsDao.findBarByRolePVICheckReject(orderId, roleId, pVIcheckScen, rejected, repeated);
    }



}
