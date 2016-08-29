package com.napkinstudio.manager;


import com.napkinstudio.dao.IUserOrderDao;
import com.napkinstudio.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserOrderManager {
    @Autowired
    private IUserOrderDao userOrderDao;

    @Transactional
    public void save(UserOrder order) {
        userOrderDao.save(order);
    }

      public List<UserOrder> findOrdersByUserId(Integer userId) {
        return userOrderDao.findOrdersByUserId(userId);
    }
}


