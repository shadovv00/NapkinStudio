package com.napkinstudio.manager;

import com.napkinstudio.dao.IUserDao;
import com.napkinstudio.entity.User;
import com.napkinstudio.util.FTPCommunicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by User1 on 20.07.2016.
 */
@Service
public class UserManager {

    @Autowired
    private IUserDao IUserDao;

    @Autowired
    private FTPCommunicator communicator;

    @Transactional
    public void save(User user) {
        user.setEnabled(true);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        IUserDao.save(user);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User findById(Integer id) {
        return IUserDao.findOne(id);
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public User findByLogin(String login) {
        return IUserDao.findByLogin(login);
    }


    public void upload(User user) throws IOException {
        communicator.convertToXMLAndUpload(user);
    }

    public User downlaod () throws IOException {
        return (User) communicator.convertToObjectAndDownload();
    }

    public User findByEmail(String email) {
        return IUserDao.findByEmail(email);
    }
}
