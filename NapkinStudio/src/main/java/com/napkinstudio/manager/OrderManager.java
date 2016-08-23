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

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }
@Transactional
public List<Order> findByUserId(Integer id) {
    return orderDao.findByUserId(id);
}
}
