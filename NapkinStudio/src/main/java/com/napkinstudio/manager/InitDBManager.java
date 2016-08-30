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

//    @PostConstruct

    public void init() {

            encoder = new BCryptPasswordEncoder();



        SAPstatus SAPStatus1 = new SAPstatus();
        SAPStatus1.setName("proof request set up");
        sapStatusDao.save(SAPStatus1);


        SAPstatus SAPStatus2 = new SAPstatus();
        SAPStatus2.setName("proof requested");
        sapStatusDao.save(SAPStatus2);


        SAPstatus SAPStatus3 = new SAPstatus();
        SAPStatus3.setName("check by PVI");
        sapStatusDao.save(SAPStatus3);

        SAPstatus SAPStatus4 = new SAPstatus();
        SAPStatus4.setName("waiting for approval");
        sapStatusDao.save(SAPStatus4);

        SAPstatus SAPStatus5 = new SAPstatus();
        SAPStatus5.setName("approved");
        sapStatusDao.save(SAPStatus5);

        SAPstatus SAPStatus6 = new SAPstatus();
        SAPStatus6.setName("rejected");
        sapStatusDao.save(SAPStatus6);

        SAPstatus SAPStatus7 = new SAPstatus();
        SAPStatus7.setName("stamps ordered");
        sapStatusDao.save(SAPStatus7);

        SAPstatus SAPStatus8 = new SAPstatus();
        SAPStatus8.setName("stamps received");
        sapStatusDao.save(SAPStatus8);

        SAPstatus SAPStatus9 = new SAPstatus();
        SAPStatus9.setName("on hold");
        sapStatusDao.save(SAPStatus9);

        SAPstatus SAPStatus10 = new SAPstatus();
        SAPStatus10.setName("order delivered");
        sapStatusDao.save(SAPStatus10);

        SAPstatus SAPStatus11 = new SAPstatus();
        SAPStatus11.setName("order entered (existing art.nr.)");
        sapStatusDao.save(SAPStatus11);


        Status status1 = new Status();
        status1.setName("Proof request set up");
        statusDao.save(status1);


        Status status2 = new Status();
        status2.setName("Proof requested");
        statusDao.save(status2);

        Status status3 = new Status();
        status3.setName("Check by PVI");
        statusDao.save(status3);


        Status status4 = new Status();
        status4.setName("Waiting for approval");
        statusDao.save(status4);

        Status status5 = new Status();
        status5.setName("Approved");
        statusDao.save(status5);

        Status status6 = new Status();
        status6.setName("Rejected");
        statusDao.save(status6);


        Status status7 = new Status();
        status7.setName("Stamps ordered");
        statusDao.save(status7);

        Status status8 = new Status();
        status8.setName("New orders");
        statusDao.save(status8);


        Status status9 = new Status();
        status9.setName("On hold");
        statusDao.save(status9);

        Status status10 = new Status();
        status10.setName("Order delivered");
        statusDao.save(status10);


        Status status11 = new Status();
        status11.setName("Existing art.nr.");
        statusDao.save(status11);

        Role debtorRole = new Role();
        debtorRole.setName("ROLE_DEPTOR");
//        debtorRole.setStatus(deptorStatus);

        Role PVIRole = new Role();
        PVIRole.setName("ROLE_PVI");
//        PVIRole.setStatus(PVIStatus);


        Role DTPRole = new Role();
        DTPRole.setName("DTP_DEPTOR");
//        debtorRole.setStatus(deptorStatus);

        Role ftpRole = new Role();
        ftpRole.setName("EXTERNALTIMER");
//        ftpRole.setStatus(ftpStatus);

        roleDao.save(debtorRole);
        roleDao.save(PVIRole);
        roleDao.save(ftpRole);
        roleDao.save(DTPRole);

            // PVI

        StatusSAPStatusRole statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus1);
        statusSAPStatusRole.setStatus(status1);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus2);
        statusSAPStatusRole.setStatus(status2);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus3);
        statusSAPStatusRole.setStatus(status3);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus4);
        statusSAPStatusRole.setStatus(status4);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus5);
        statusSAPStatusRole.setStatus(status5);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus6);
        statusSAPStatusRole.setStatus(status6);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus7);
        statusSAPStatusRole.setStatus(status7);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus1);
        statusSAPStatusRole.setStatus(status1);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus8);
        statusSAPStatusRole.setStatus(status8);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus9);
        statusSAPStatusRole.setStatus(status9);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus10);
        statusSAPStatusRole.setStatus(status10);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(PVIRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus11);
        statusSAPStatusRole.setStatus(status11);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);



        //DEPTOR
        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus1);
        statusSAPStatusRole.setStatus(status1);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus2);
        statusSAPStatusRole.setStatus(status1);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus3);
        statusSAPStatusRole.setStatus(status1);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus4);
        statusSAPStatusRole.setStatus(status4);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus5);
        statusSAPStatusRole.setStatus(status5);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus7);
        statusSAPStatusRole.setStatus(status5);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus6);
        statusSAPStatusRole.setStatus(status6);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus8);
        statusSAPStatusRole.setStatus(status8);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus9);
        statusSAPStatusRole.setStatus(status9);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus10);
        statusSAPStatusRole.setStatus(status10);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus11);
        statusSAPStatusRole.setStatus(status11);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        /////DTP


        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus2);
        statusSAPStatusRole.setStatus(status2);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus3);
        statusSAPStatusRole.setStatus(status3);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus4);
        statusSAPStatusRole.setStatus(status4);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus5);
        statusSAPStatusRole.setStatus(status5);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus6);
        statusSAPStatusRole.setStatus(status6);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus7);
        statusSAPStatusRole.setStatus(status7);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus8);
        statusSAPStatusRole.setStatus(status7);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus10);
        statusSAPStatusRole.setStatus(status7);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(DTPRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus9);
        statusSAPStatusRole.setStatus(status9);
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


        User DTP = new User();
        DTP.setFirstName("Kevin");
        DTP.setLastName("Parker");
        DTP.setLogin("DTP");
        DTP.setPassword(encoder.encode("DTP"));
        DTP.setEnabled(true);
        List<Role> roles3 = new ArrayList<Role>();
        roles3.add(DTPRole);
        DTP.setRoles(roles3);

        User ftpUser = new User();
        ftpUser.setFirstName("ftp");
        ftpUser.setLastName("expire");
        ftpUser.setLogin("ftp");
        ftpUser.setPassword("ftp");
        ftpUser.setEnabled(true);
        List<Role> roles4 = new ArrayList<Role>();
        roles4.add(ftpRole);
        ftpUser.setRoles(roles4);

        userDao.save(debtor);
        userDao.save(PVI);
        userDao.save(ftpUser);
        userDao.save(DTP);

        Order order1 = new Order();
        order1.setSAPstatus(SAPStatus7);
        order1.setDebItemNum("1234567");
        order1.setItemNum("1678672");
        order1.setPrintName("Crowne Plaza 24 standaard");
        order1.setOrderId(1402130001);
        order1.setDeliveryDate(new Date());
        orderDao.save(order1);

        UserOrder user1Order = new UserOrder();
        user1Order.setUser(debtor);
        user1Order.setOrder(order1);
        user1Order.setLastLook(new Date());

        UserOrder user2Order = new UserOrder();
        user2Order.setUser(PVI);
        user2Order.setOrder(order1);
        user2Order.setLastLook(new Date());


        UserOrder user3Order = new UserOrder();
        user3Order.setUser(DTP);
        user3Order.setOrder(order1);
        user3Order.setLastLook(new Date());


        userOrderDao.save(user1Order);
        userOrderDao.save(user2Order);
        userOrderDao.save(user3Order);

        Order order3 = new Order();
        order3.setSAPstatus(SAPStatus11);
        order3.setDebItemNum("063821232");
        order3.setItemNum("69784673");
        order3.setPrintName("Crowne Plaza 24 standaard");
        order3.setOrderId(1402130003);
        order3.setDeliveryDate(new Date());
        orderDao.save(order3);

        UserOrder user1Order3 = new UserOrder();
        user1Order3.setUser(debtor);
        user1Order3.setOrder(order3);
        user1Order3.setLastLook(new Date());

        UserOrder user2Order3 = new UserOrder();
        user2Order3.setUser(PVI);
        user2Order3.setOrder(order3);
        user2Order3.setLastLook(new Date());


        UserOrder user3Order3 = new UserOrder();
        user3Order3.setUser(DTP);
        user3Order3.setOrder(order3);
        user3Order3.setLastLook(new Date());


        userOrderDao.save(user1Order3);
        userOrderDao.save(user2Order3);
        userOrderDao.save(user3Order3);

        Order order2 = new Order();
        order2.setSAPstatus(SAPStatus3);
        order2.setDebItemNum("2559752");
        order2.setItemNum("0934895");
        order2.setPrintName("Crowne Plaza 24 standaard");
        order2.setOrderId(1402130002);
        order2.setDeliveryDate(new Date());
        orderDao.save(order2);

        UserOrder user1Order2 = new UserOrder();
        user1Order2.setUser(debtor);
        user1Order2.setOrder(order2);
        user1Order2.setLastLook(new Date());

        UserOrder user2Order2 = new UserOrder();
        user2Order2.setUser(PVI);
        user2Order2.setOrder(order2);
        user2Order2.setLastLook(new Date());


        UserOrder userO3rder2 = new UserOrder();
        userO3rder2.setUser(DTP);
        userO3rder2.setOrder(order2);
        userO3rder2.setLastLook(new Date());


        userOrderDao.save(user1Order2);
        userOrderDao.save(user2Order2);
        userOrderDao.save(userO3rder2);





    }


}
