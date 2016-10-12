package com.napkinstudio.manager;

import com.napkinstudio.dao.IOrderDao;
import com.napkinstudio.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;


@Service
public class OrderManager {

@Autowired
private IOrderDao orderDao;

    @Transactional
    public void save(Order order) {
        orderDao.save(order);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Order findById(Integer id) {
        return orderDao.findOne(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public LinkedList<Order> getUpdatedOrders(Date lastModifiedDate) {
        return orderDao.getUpdatedOrders(lastModifiedDate);
    }



//    @Transactional
//    public getOne (Integer orderId) {
//        return orderDao.getOne(orderId);
//    }


//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
