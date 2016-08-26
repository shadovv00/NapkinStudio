package com.napkinstudio.manager;

import com.napkinstudio.dao.IOrderDao;
import com.napkinstudio.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderManager {

@Autowired
private IOrderDao orderDao;


    @Transactional
    public void save(Order order) {
        orderDao.save(order);
    }


//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
