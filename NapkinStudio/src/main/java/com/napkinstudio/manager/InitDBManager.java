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

    @Autowired
    private IProgresBarFieldsDao progresBarFieldsDao ;


    private BCryptPasswordEncoder encoder;

    @Autowired
    private ICommentsDao commentsDao;

    @Autowired
    private IStatusChangeDao statusChangeDao;

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

        Role stampManufacRole = new Role();
        stampManufacRole.setName("ROLE_STAMPMANUFACTURER");

        Role productionRole = new Role();
        productionRole.setName("ROLE_PRODUCTION");

        Role debtorRole = new Role();
        debtorRole.setName("ROLE_DEPTOR");
//        debtorRole.setStatus(deptorStatus);

        Role PVIRole = new Role();
        PVIRole.setName("ROLE_PVI");
//        PVIRole.setStatus(PVIStatus);


        Role DTPRole = new Role();
        DTPRole.setName("ROLE_DTP");
//        debtorRole.setStatus(deptorStatus);

        Role ftpRole = new Role();
        ftpRole.setName("EXTERNALTIMER");
//        ftpRole.setStatus(ftpStatus);

        Role CustomRole = new Role();
        CustomRole.setName("ROLE_CUSTOMER");

        roleDao.save(debtorRole);
        roleDao.save(PVIRole);
        roleDao.save(ftpRole);
        roleDao.save(DTPRole);
        roleDao.save(CustomRole);
        roleDao.save(stampManufacRole);
        roleDao.save(productionRole);

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
        statusSAPStatusRole.setStatus(status2);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus2);
        statusSAPStatusRole.setStatus(status2);
        statusSAPStatusRoleDao.save(statusSAPStatusRole);

        statusSAPStatusRole = new StatusSAPStatusRole();
        statusSAPStatusRole.setRole(debtorRole);
        statusSAPStatusRole.setSAPStatus(SAPStatus3);
        statusSAPStatusRole.setStatus(status2);
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
        debtor.setEmail("khomenkotest1@gmail.com");
//        List<Role> roles = new ArrayList<Role>();
//        roles.add(debtorRole);
//        debtor.setRoles(roles);
        debtor.setRole(debtorRole);

        User PVI = new User();
        PVI.setFirstName("Mark");
        PVI.setLastName("Cool");
        PVI.setLogin("PVI1");
        PVI.setPassword(encoder.encode("PVI1"));
        PVI.setEnabled(true);
        PVI.setEmail("khomenkotest1@gmail.com");
//        List<Role> roles2 = new ArrayList<Role>();
//        roles2.add(PVIRole);
//        PVI.setRoles(roles2);
        PVI.setRole(PVIRole);


        User DTP = new User();
        DTP.setFirstName("Kevin");
        DTP.setLastName("Parker");
        DTP.setLogin("DTP");
        DTP.setPassword(encoder.encode("DTP"));
        DTP.setEnabled(true);
        DTP.setEmail("khomenkotest1@gmail.com");
//        List<Role> roles3 = new ArrayList<Role>();
//        roles3.add(DTPRole);
//        DTP.setRoles(roles3);
        DTP.setRole(DTPRole);

        User ftpUser = new User();
        ftpUser.setFirstName("ftp");
        ftpUser.setLastName("expire");
        ftpUser.setLogin("ftp");
        ftpUser.setPassword("ftp");
        ftpUser.setEnabled(true);
//        List<Role> roles4 = new ArrayList<Role>();
//        roles4.add(ftpRole);
//        ftpUser.setRoles(roles4);
        ftpUser.setRole(ftpRole);

        User CustomUser = new User();
        CustomUser.setFirstName("Customer");
        CustomUser.setLastName("Customer");
        CustomUser.setLogin("Customer");
        CustomUser.setEmail("khomenkotest1@gmail.com");
        CustomUser.setPassword(encoder.encode("Customer"));
        CustomUser.setEnabled(true);
//        List<Role> roles5 = new ArrayList<Role>();
//        roles5.add(CustomRole);
//        CustomUser.setRoles(roles5);
        CustomUser.setRole(CustomRole);


        userDao.save(debtor);
        userDao.save(PVI);
        userDao.save(ftpUser);
        userDao.save(DTP);
        userDao.save(CustomUser);





        Order order1 = new Order();
        order1.setSAPstatus(SAPStatus1);
        order1.setDebItemNum("1234567");
        order1.setItemNum("1678672");
        order1.setPrintName("Crowne Plaza 24 standaard");
        order1.setOrderId(1402130001);
        order1.setDeliveryDate(new Date());
        order1.setPVIcheckScen(false);
        order1.setRejected(false);
        order1.setApprovalBy("Deptor");
        order1.setDebCheckScen(true);
//        order1.setComments(commentsList);
        orderDao.save(order1);


        StatusChange statchang1 = new StatusChange();
        statchang1.setOrder(order1);
        statchang1.setSAPstatus(SAPStatus1);
        statchang1.setDateTime(new Date());
        statusChangeDao.save(statchang1);
        StatusChange statchang3 = new StatusChange();
        statchang3.setOrder(order1);
        statchang3.setSAPstatus(SAPStatus1);
        statchang3.setDateTime(new Date());
        statusChangeDao.save(statchang3);
        StatusChange statchang2 = new StatusChange();
        statchang2.setOrder(order1);
        statchang2.setSAPstatus(SAPStatus1);
        statchang2.setDateTime(new Date());
        statusChangeDao.save(statchang2);

//description of ProcessIds
/*      1 "to deptor first"
        2 "to user"
        3 "to deptor fin"
        4 "to dtp"
        5 "to cliche"
*/



        /////COMMENTS
        List<Comments> commentsList = new ArrayList<>();

        Comments comment = new Comments();
        comment.setCommText("Hello NapkinStudio in PVI group");
        comment.setForRole(PVIRole);
        comment.setFromUser(debtor);

        comment.setOrder(order1);
        commentsDao.save(comment);

        Comments comment1 = new Comments();
        comment1.setCommText("Hello NapkinStudio  in debtor group");
        comment1.setForRole(debtorRole);
        comment1.setFromUser(PVI);

        comment1.setOrder(order1);
        commentsDao.save(comment1);

        Comments comment2 = new Comments();
        comment2.setCommText("Hello NapkinStudio in DTP Group");
        comment2.setForRole(DTPRole);
        comment2.setFromUser(PVI);
        comment2.setOrder(order1);

        commentsDao.save(comment2);

        Comments commen3 = new Comments();
        commen3.setCommText("Hello NapkinStudio in end customer group");
        commen3.setForRole(CustomRole);
        commen3.setFromUser(PVI);

        commen3.setOrder(order1);
        commentsDao.save(commen3);

        Comments comment4 = new Comments();
        comment4.setCommText("Hello NapkinStudio in  stamp manufacturer group");
        comment4.setForRole(stampManufacRole);
        comment4.setFromUser(PVI);

        comment4.setOrder(order1);
        commentsDao.save(comment4);

        Comments commen5 = new Comments();
        commen5.setCommText("Hello NapkinStudio in production group");
        commen5.setForRole(productionRole);
        commen5.setFromUser(PVI);

        commen5.setOrder(order1);
        commentsDao.save(commen5);

        Comments commen6 = new Comments();
        commen6.setCommText("Hello NapkinStudio in PVI Group 2");
        commen6.setForRole(PVIRole);
        commen6.setFromUser(PVI);

        commen6.setOrder(order1);
        commentsDao.save(commen6);



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

        UserOrder user5Order = new UserOrder();
        user5Order.setUser(CustomUser);
        user5Order.setOrder(order1);
        user5Order.setLastLook(new Date());



        userOrderDao.save(user1Order);
        userOrderDao.save(user2Order);
        userOrderDao.save(user3Order);
//        userOrderDao.save(user5Order);

        Order order3 = new Order();
        order3.setSAPstatus(SAPStatus4);
        order3.setDebItemNum("063821232");
        order3.setItemNum("69784673");
        order3.setPrintName("Crowne Plaza 24 standaard");
        order3.setOrderId(1402130003);
        order3.setDeliveryDate(new Date());
        order3.setPVIcheckScen(true);
        order3.setRejected(false);
        order3.setDebCheckScen(true);
        order3.setApprovalBy("Customer");
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

        UserOrder user5Order3 = new UserOrder();
        user5Order3.setUser(CustomUser);
        user5Order3.setOrder(order3);
        user5Order3.setLastLook(new Date());


        userOrderDao.save(user1Order3);
        userOrderDao.save(user2Order3);
        userOrderDao.save(user3Order3);
        userOrderDao.save(user5Order3);

        Order order2 = new Order();
        order2.setSAPstatus(SAPStatus3);
        order2.setDebItemNum("2559752");
        order2.setItemNum("0934895");
        order2.setPrintName("Crowne Plaza 24 standaard");
        order2.setOrderId(1402130002);
        order2.setDeliveryDate(new Date());
        order2.setPVIcheckScen(false);
        order2.setRejected(false);
        order2.setDebCheckScen(false);
        order2.setApprovalBy("Customer");
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

        UserOrder userO5rder2 = new UserOrder();
        userO5rder2.setUser(CustomUser);
        userO5rder2.setOrder(order2);
        userO5rder2.setLastLook(new Date());


        userOrderDao.save(user1Order2);
        userOrderDao.save(user2Order2);
        userOrderDao.save(userO3rder2);
        userOrderDao.save(userO5rder2);


// setting Fields for Progress Bar
        ////////PVI//////////////////////////////////////////////no pvi check, no rej
        Status status99 = new Status();
        status99.setName("Stamps received");
        statusDao.save(status99);

        Status status98 = new Status();
        status98.setName("Planned");
        statusDao.save(status98);


        ProgresBarFields proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status1);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(PVIRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

        ProgresBarFields proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(PVIRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

        ProgresBarFields waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(PVIRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

        ProgresBarFields appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(PVIRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(false);
        progresBarFieldsDao.save(appr);

        ProgresBarFields stordr = new ProgresBarFields();
        stordr.setStatus(status7);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(PVIRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(false);
        progresBarFieldsDao.save(stordr);

        ProgresBarFields stres = new ProgresBarFields();
        stres.setStatus(status99);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(PVIRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(false);
        progresBarFieldsDao.save(stres);

        ProgresBarFields stres1 = new ProgresBarFields();
        stres1.setStatus(status99);
        stres1.setSAPStatus(SAPStatus11);
        stres1.setRole(PVIRole);
        stres1.setRejected(false);
        stres1.setPVIcheckScen(false);
        progresBarFieldsDao.save(stres1);

        ProgresBarFields onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(PVIRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        ProgresBarFields orddel = new ProgresBarFields();
        orddel.setStatus(status10);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(PVIRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(false);
        progresBarFieldsDao.save(orddel);
////////PVI//////////////////////////////////////////////no pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status1);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(PVIRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(PVIRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(PVIRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

        ProgresBarFields rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(PVIRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(false);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(PVIRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        ////////PVI////////////////////////////////////////////// pvi check, no rej
         proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status1);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(PVIRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

         proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(PVIRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        ProgresBarFields checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status3);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(PVIRole);
        checkPvi.setRejected(false);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

         waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(PVIRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

         appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(PVIRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(true);
        progresBarFieldsDao.save(appr);

         stordr = new ProgresBarFields();
        stordr.setStatus(status7);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(PVIRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(true);
        progresBarFieldsDao.save(stordr);

         stres = new ProgresBarFields();
        stres.setStatus(status99);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(PVIRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(true);
        progresBarFieldsDao.save(stres);

         stres1 = new ProgresBarFields();
        stres1.setStatus(status99);
        stres1.setSAPStatus(SAPStatus11);
        stres1.setRole(PVIRole);
        stres1.setRejected(false);
        stres1.setPVIcheckScen(true);
        progresBarFieldsDao.save(stres1);

         onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(PVIRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);

        orddel = new ProgresBarFields();
        orddel.setStatus(status10);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(PVIRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(true);
        progresBarFieldsDao.save(orddel);
////////PVI////////////////////////////////////////////// pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status1);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(PVIRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(PVIRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status3);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(PVIRole);
        checkPvi.setRejected(true);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(PVIRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

         rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(PVIRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(true);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(PVIRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);


        ////////Debtor//////////////////////////////////////////////no pvi check, no rej
         proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(debtorRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

         proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(debtorRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

         waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(debtorRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

         appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(debtorRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(false);
        progresBarFieldsDao.save(appr);

         stordr = new ProgresBarFields();
        stordr.setStatus(status98);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(debtorRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(false);
        progresBarFieldsDao.save(stordr);

         stres = new ProgresBarFields();
        stres.setStatus(status98);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(debtorRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(false);
        progresBarFieldsDao.save(stres);

        stres1 = new ProgresBarFields();
        stres1.setStatus(status98);
        stres1.setSAPStatus(SAPStatus11);
        stres1.setRole(debtorRole);
        stres1.setRejected(false);
        stres1.setPVIcheckScen(false);
        progresBarFieldsDao.save(stres1);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(debtorRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        orddel = new ProgresBarFields();
        orddel.setStatus(status10);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(debtorRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(false);
        progresBarFieldsDao.save(orddel);
////////Debtor//////////////////////////////////////////////no pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(debtorRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(debtorRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(debtorRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

        rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(debtorRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(false);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(debtorRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        ////////Debtor////////////////////////////////////////////// pvi check, no rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(debtorRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(debtorRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status2);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(debtorRole);
        checkPvi.setRejected(false);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(debtorRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

        appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(debtorRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(true);
        progresBarFieldsDao.save(appr);

        stordr = new ProgresBarFields();
        stordr.setStatus(status98);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(debtorRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(true);
        progresBarFieldsDao.save(stordr);

        stres = new ProgresBarFields();
        stres.setStatus(status98);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(debtorRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(true);
        progresBarFieldsDao.save(stres);

        stres1 = new ProgresBarFields();
        stres1.setStatus(status98);
        stres1.setSAPStatus(SAPStatus11);
        stres1.setRole(debtorRole);
        stres1.setRejected(false);
        stres1.setPVIcheckScen(true);
        progresBarFieldsDao.save(stres1);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(debtorRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);

        orddel = new ProgresBarFields();
        orddel.setStatus(status10);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(debtorRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(true);
        progresBarFieldsDao.save(orddel);
////////Debtor////////////////////////////////////////////// pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(debtorRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(debtorRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status2);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(debtorRole);
        checkPvi.setRejected(true);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(debtorRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

        rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(debtorRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(true);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(debtorRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);


 ////////DTP//////////////////////////////////////////////no pvi check, no rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(DTPRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(DTPRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(DTPRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

        appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(DTPRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(false);
        progresBarFieldsDao.save(appr);

        stordr = new ProgresBarFields();
        stordr.setStatus(status7);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(DTPRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(false);
        progresBarFieldsDao.save(stordr);

        stres = new ProgresBarFields();
        stres.setStatus(status7);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(DTPRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(false);
        progresBarFieldsDao.save(stres);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(DTPRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        orddel = new ProgresBarFields();
        orddel.setStatus(status7);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(DTPRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(false);
        progresBarFieldsDao.save(orddel);
////////DTP//////////////////////////////////////////////no pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(DTPRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(DTPRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(false);
        progresBarFieldsDao.save(proofRequest);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(DTPRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(false);
        progresBarFieldsDao.save(waitForAppr);

        rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(DTPRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(false);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(DTPRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(false);
        progresBarFieldsDao.save(onhold);

        ////////DTP////////////////////////////////////////////// pvi check, no rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(DTPRole);
        proofRequestSetUp.setRejected(false);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(DTPRole);
        proofRequest.setRejected(false);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status3);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(DTPRole);
        checkPvi.setRejected(false);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(DTPRole);
        waitForAppr.setRejected(false);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

        appr = new ProgresBarFields();
        appr.setStatus(status5);
        appr.setSAPStatus(SAPStatus5);
        appr.setRole(DTPRole);
        appr.setRejected(false);
        appr.setPVIcheckScen(true);
        progresBarFieldsDao.save(appr);

        stordr = new ProgresBarFields();
        stordr.setStatus(status7);
        stordr.setSAPStatus(SAPStatus7);
        stordr.setRole(DTPRole);
        stordr.setRejected(false);
        stordr.setPVIcheckScen(true);
        progresBarFieldsDao.save(stordr);

        stres = new ProgresBarFields();
        stres.setStatus(status7);
        stres.setSAPStatus(SAPStatus8);
        stres.setRole(DTPRole);
        stres.setRejected(false);
        stres.setPVIcheckScen(true);
        progresBarFieldsDao.save(stres);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(DTPRole);
        onhold.setRejected(false);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);

        orddel = new ProgresBarFields();
        orddel.setStatus(status7);
        orddel.setSAPStatus(SAPStatus10);
        orddel.setRole(DTPRole);
        orddel.setRejected(false);
        orddel.setPVIcheckScen(true);
        progresBarFieldsDao.save(orddel);
////////DTP////////////////////////////////////////////// pvi check, rej
        proofRequestSetUp = new ProgresBarFields();
        proofRequestSetUp.setStatus(status2);
        proofRequestSetUp.setSAPStatus(SAPStatus1);
        proofRequestSetUp.setRole(DTPRole);
        proofRequestSetUp.setRejected(true);
        proofRequestSetUp.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequestSetUp);

        proofRequest = new ProgresBarFields();
        proofRequest.setStatus(status2);
        proofRequest.setSAPStatus(SAPStatus2);
        proofRequest.setRole(DTPRole);
        proofRequest.setRejected(true);
        proofRequest.setPVIcheckScen(true);
        progresBarFieldsDao.save(proofRequest);

        checkPvi = new ProgresBarFields();
        checkPvi.setStatus(status3);
        checkPvi.setSAPStatus(SAPStatus3);
        checkPvi.setRole(DTPRole);
        checkPvi.setRejected(true);
        checkPvi.setPVIcheckScen(true);
        progresBarFieldsDao.save(checkPvi);

        waitForAppr = new ProgresBarFields();
        waitForAppr.setStatus(status4);
        waitForAppr.setSAPStatus(SAPStatus4);
        waitForAppr.setRole(DTPRole);
        waitForAppr.setRejected(true);
        waitForAppr.setPVIcheckScen(true);
        progresBarFieldsDao.save(waitForAppr);

        rej = new ProgresBarFields();
        rej.setStatus(status6);
        rej.setSAPStatus(SAPStatus6);
        rej.setRole(DTPRole);
        rej.setRejected(true);
        rej.setPVIcheckScen(true);
        progresBarFieldsDao.save(rej);

        onhold = new ProgresBarFields();
        onhold.setStatus(status9);
        onhold.setSAPStatus(SAPStatus9);
        onhold.setRole(DTPRole);
        onhold.setRejected(true);
        onhold.setPVIcheckScen(true);
        progresBarFieldsDao.save(onhold);




//        ProgresBarFields proofRequest = new ProgresBarFields();
//        proofRequest.setName("proof request set up");
//        List<SAPstatus> prSAPList = new ArrayList<SAPstatus>();
//        prSAPList.add(SAPStatus1);
//        prSAPList.add(SAPStatus2);
//        proofRequest.setSAPstatus(prSAPList);
//        proofRequest.setRoles(roles2);
//        progresBarFieldsDao.save(proofRequest);




//        proof request set up  (1) > proof requested (2) > waiting for approval (4) > approved  (5) > stamps ordered (7) > stamps received (8, 11) > order delivered (10)

    }


}
