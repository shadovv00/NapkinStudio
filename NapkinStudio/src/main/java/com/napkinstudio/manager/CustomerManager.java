package com.napkinstudio.manager;

import com.napkinstudio.dao.ICustomerDao;
import com.napkinstudio.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerManager {

@Autowired
private ICustomerDao customerDao;

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
