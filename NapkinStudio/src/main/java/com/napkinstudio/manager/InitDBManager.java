package com.napkinstudio.manager;

import com.napkinstudio.dao.*;
import com.napkinstudio.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    ISAPstatusDao sapStatusDao;

    @Autowired
    IArticleDao articleDao;

    @Autowired
    private IOrderDao orderDao;

    @Autowired
    private IUserOrderDao userOrderDao;

    @Autowired
    private IStatusSAPStatusRole statusSAPStatusRoleDao;

    private BCryptPasswordEncoder encoder;

    @PostConstruct

    public void init() {

            encoder = new BCryptPasswordEncoder();






//
//      SAPstatus SAPStatus = new SAPstatus();
//        SAPStatus.setName("proof request set up");
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("proof requested");
//        portalSAPStatuses1.add(SAPStatus);
//        portalSAPStatuses2.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("check by PVI");
//        portalSAPStatuses1.add(SAPStatus);
//        portalSAPStatuses3.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("waiting for approval");
//        portalSAPStatuses4.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("approved");
//        portalSAPStatuses5.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("rejected");
//        portalSAPStatuses6.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("stamps ordered");
//        portalSAPStatuses7.add(SAPStatus);
//        portalSAPStatuses5.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("stamps received");
//        portalSAPStatuses8.add(SAPStatus);
//        portalSAPStatuses7.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("on hold");
//        portalSAPStatuses9.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);
//
//        SAPStatus = new SAPstatus();
//        SAPStatus.setName("order delivered");
//        portalSAPStatuses10.add(SAPStatus);
//        portalSAPStatuses7.add(SAPStatus);
//        sapStatusDao.save(SAPStatus);



        List<Status> deptorStatus = new ArrayList<Status>();
        List<Status> PVIStatus = new ArrayList<Status>();
        List<Status> ftpStatus = new ArrayList<Status>();

//        Status s1 = new Status();
//        s1.setName("Proof request set up");
//        s1.setSAPstatuses(portalSAPStatuses1);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Proof requested");
//        s1.setSAPstatuses(portalSAPStatuses2);
//        PVIStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Check by PVI");
//        s1.setSAPstatuses(portalSAPStatuses3);
//        PVIStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Waiting for approval");
//        s1.setSAPstatuses(portalSAPStatuses4);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Approved");
//        s1.setSAPstatuses(portalSAPStatuses5);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Rejected");
//        s1.setSAPstatuses(portalSAPStatuses6);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);
//
//
//        s1 = new Status();
//        s1.setName("Stamps ordered");
//        s1.setSAPstatuses(portalSAPStatuses7);
//        PVIStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("New orders");
//        s1.setSAPstatuses(portalSAPStatuses8);
//        PVIStatus.add(s1);
//        statusDao.save(s1);
//
//
//        s1 = new Status();
//        s1.setName("On hold");
//        s1.setSAPstatuses(portalSAPStatuses9);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);
//
//        s1 = new Status();
//        s1.setName("Order delivered");
//        s1.setSAPstatuses(portalSAPStatuses10);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
//        statusDao.save(s1);

        SAPstatus  SAPStatus = new SAPstatus();
        SAPStatus.setName("order entered (existing art.nr.)");
//        portalSAPStatuses11.add(SAPStatus);
        sapStatusDao.save(SAPStatus);

        Status s1 = new Status();
        s1.setName("Existing art.nr.");
//        s1.setSAPstatuses(portalSAPStatuses11);
//        PVIStatus.add(s1);
//        deptorStatus.add(s1);
        statusDao.save(s1);

        Role debtorRole = new Role();
        debtorRole.setName("ROLE_DEPTOR");
//        debtorRole.setStatus(deptorStatus);

        Role PVIRole = new Role();
        PVIRole.setName("ROLE_PVI");
//        PVIRole.setStatus(PVIStatus);

        Role ftpRole = new Role();
        ftpRole.setName("EXTERNALTIMER");
//        ftpRole.setStatus(ftpStatus);

        roleDao.save(debtorRole);
        roleDao.save(PVIRole);
        roleDao.save(ftpRole);

        StatusSAPStatusRole statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setSAPStatus(SAPStatus);
        statusSAPStatusRole.setStatus(s1);
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);



        User debtor = new User();
        debtor.setFirstName("Teddy");
        debtor.setLastName("Test");
        debtor.setLogin("deptor1");
        debtor.setPassword(encoder.encode("deptor1"));
        debtor.setEnabled(true);
        List<Role> roles = new ArrayList<Role>();
        roles.add(debtorRole);
        debtor.setRoles(roles);

        User PVI = new User();
        PVI.setFirstName("Mark");
        PVI.setLastName("Cool");
        PVI.setLogin("PVI1");
        PVI.setPassword(encoder.encode("PVI1"));
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

        Order order1 = new Order();
//        List<User> users = new ArrayList<>();
//        users.add(PVI);
//        users.add(debtor);
//      order1.setUsers(users);
        order1.setSAPstatus(SAPStatus);
        order1.setOrderId(1);
        orderDao.save(order1);

    UserOrder userOrder = new UserOrder();
        userOrder.setUser(debtor);
        userOrder.setOrder(order1);
        userOrder.setLastLook(new Date());

        UserOrder userOrder1 = new UserOrder();
        userOrder1.setUser(PVI);
        userOrder1.setOrder(order1);
        userOrder1.setLastLook(new Date());


    userOrderDao.save(userOrder);
        userOrderDao.save(userOrder1);


    }


}
