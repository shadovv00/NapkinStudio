package com.napkinstudio.manager;

import com.napkinstudio.dao.IRoleDao;
import com.napkinstudio.dao.IStatusDao;
import com.napkinstudio.dao.IUserDao;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.Status;
import com.napkinstudio.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User1 on 20.07.2016.
 */
@Service
@Transactional
public class InitDBManager {

    @Autowired
    IUserDao userDao;

    @Autowired
    IRoleDao roleDao;

    @Autowired
    IStatusDao statusDao;

//    @PostConstruct
    public void init(){

        List<Status> deptorStatus = new ArrayList<Status>();
        List<Status> PVIStatus = new ArrayList<Status>();
        List<Status> ftpStatus = new ArrayList<Status>();

        Status s1 = new Status();
        s1.setName("proof request set up");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("proof requested");
        PVIStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("check by PVI");
        PVIStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("waiting for approval");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("approved");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("rejected");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("stamps ordered");
        PVIStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("stamps received");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("On hold");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("Order delivered");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        s1 = new Status();
        s1.setName("Repeat order (existing art.nr.)");
        PVIStatus.add(s1);
        deptorStatus.add(s1);
        statusDao.save(s1);

        Role debtorRole = new Role();
        debtorRole.setName("ROLE_DEPTOR");
        debtorRole.setStatus(deptorStatus);

        Role PVIRole = new Role();
        PVIRole.setName("ROLE_PVI");
        PVIRole.setStatus(PVIStatus);
        
        Role ftpRole = new Role();
        ftpRole.setName("EXTERNALTIMER");
        ftpRole.setStatus(ftpStatus);

        roleDao.save(debtorRole);
        roleDao.save(PVIRole);
        roleDao.save(ftpRole);
        

        User debtor = new User();
        debtor.setFirstName("Teddy");
        debtor.setLastName("Test");
        debtor.setLogin("deptor1");
        debtor.setPassword("deptor1");
        debtor.setEnabled(true);
        List<Role> roles = new ArrayList<Role>();
        roles.add(debtorRole);
        debtor.setRoles(roles);

        User PVI = new User();
        PVI.setFirstName("Mark");
        PVI.setLastName("Cool");
        PVI.setLogin("PVI1");
        PVI.setPassword("PVI1");
        PVI.setEnabled(true);
        List<Role> roles2 = new ArrayList<Role>();
        roles2.add(PVIRole);
        PVI.setRoles(roles2);
        
        User ftpUser = new User();
        ftpUser.setFirstName("ftp");
        ftpUser.setLastName("expire");
        ftpUser.setLogin("ftp");
        ftpUser.setPassword("ftp");
        ftpUser.setEnabled(true);
        List<Role> roles3 = new ArrayList<Role>();
        roles3.add(ftpRole);
        ftpUser.setRoles(roles3);

        userDao.save(debtor);
        userDao.save(PVI);
        userDao.save(ftpUser);
    }


}
