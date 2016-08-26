package com.napkinstudio.manager;


import com.napkinstudio.dao.IUser_ordersDao;
import com.napkinstudio.entity.User_orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User_ordersManager {
    @Autowired
    private IUser_ordersDao user_ordersDao;
    @Transactional
    public void save(User_orders order) {
        user_ordersDao.save(order);
    }

}
