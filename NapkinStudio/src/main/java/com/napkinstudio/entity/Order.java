package com.napkinstudio.entity;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import com.napkinstudio.entity.Article;
import com.napkinstudio.entity.Customer;
import com.napkinstudio.entity.User;
import com.napkinstudio.entity.StatusChange;
import com.napkinstudio.entity.SAPstatus;

import static javax.persistence.CacheStoreMode.REFRESH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@Entity

@NamedQueries({
//        @NamedQuery(name = "User.findAllByFirstName", query = "SELECT u FROM  User  u  WHERE u.firstName =:firstName"),
//        @NamedQuery(name = "User.findAllByLastName", query = "SELECT u FROM  User  u  WHERE u.lastName  =:lastName"),
//        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM  User  u  WHERE u.login   =:login"),
//        @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.userId = ?1"),
        /*@NamedQuery(name = "User.deactivateById", query = "update User as u set u.enabled =0  where u.userId = ?1"),
        @NamedQuery(name = "User.activateById", query = "update User as u set u.enabled =1  where u.userId = ?1"),*/ })

@Table(name = "ORDERS")
public class Order {

 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String debNum;
    private String debOrderNum;
    private String quantity;
    private String approvalBy;
    private String debCont;
    private String delivAddr;
    private String delivAddrCont;
    private String unloadTimes;
    private String delivInstruct;
    private String pVIcheckScen;
    private String debCheckScen;
    private Boolean toDeptor;
    private Boolean deleted;
    private Date lastUpdate;
    
    
    @OneToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private Article article;
    public Article getArticle() {
        return article;    }
    public void setArticle(Article article) {
        this.article = article;    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private Customer customer;
    public Customer getCustomer() {
        return customer;    }
    public void setCustomer(Customer customer) {
        this.customer = customer;    }
       
    @ManyToMany(mappedBy="orders",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<User> users;
    public List<User> getUsers() {
        return users;    }
    public void setUsers(List<User> users) {
        this.users = users;    }

    @OneToMany(mappedBy="order",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<Comments> comments;
    public List<Comments> getComments() {
        return comments;    }
    public void setComments(List<Comments> comments) {
        this.comments = comments;    }

    @OneToMany(mappedBy="order",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<StatusChange> statusChanges;
    public List<StatusChange> getStatusChanges() {
        return statusChanges;    }
    public void setStatusChanges(List<StatusChange> statusChanges) {
        this.statusChanges = statusChanges;    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private SAPstatus sapStatus;
    public SAPstatus getSAPstatus() {
        return sapStatus;    }
    public void setSAPstatus(SAPstatus sapStatus) {
        this.sapStatus = sapStatus;    }
       
    
    public String getDebNum() {
        return debNum;    }   
    public void setDebNum(String debNum) {
        this.debNum = debNum;    }

    public String getDebOrderNum() {
        return debOrderNum;    }   
    public void setDebOrderNum(String debOrderNum) {
        this.debOrderNum = debOrderNum;    }

    public String getQuantity() {
        return quantity;    }   
    public void setQuantity(String quantity) {
        this.quantity = quantity;    }

    public String getApprovalBy() {
        return approvalBy;    }   
    public void setApprovalBy(String approvalBy) {
        this.approvalBy = approvalBy;    }

    public String getDebCont() {
        return debCont;    }   
    public void setDebCont(String debCont) {
        this.debCont = debCont;    }

    public String getDelivAddr() {
        return delivAddr;    }   
    public void setDelivAddr(String delivAddr) {
        this.delivAddr = delivAddr;    }

    public String getDelivAddrCont() {
        return delivAddrCont;    }   
    public void setDelivAddrCont(String delivAddrCont) {
        this.delivAddrCont = delivAddrCont;    }

    public String getUnloadTimes() {
        return unloadTimes;    }   
    public void setUnloadTimes(String unloadTimes) {
        this.unloadTimes = unloadTimes;    }

    public String getDelivInstruct() {
        return delivInstruct;    }   
    public void setDelivInstruct(String delivInstruct) {
        this.delivInstruct = delivInstruct;    }

    public String getPVIcheckScen() {
        return pVIcheckScen;    }   
    public void setPVIcheckScen(String pVIcheckScen) {
        this.pVIcheckScen = pVIcheckScen;    }

    public String getDebCheckScen() {
        return debCheckScen;    }   
    public void sebDebCheckScen(String debCheckScen) {
        this.debCheckScen = debCheckScen;    }

    public Boolean getToDeptor() {
        return toDeptor;    }
    public void setToDeptor(Boolean toDeptor) {
        this.toDeptor = toDeptor;    }

    public Boolean getDeleted() {
        return deleted;    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;    }

	public Date getUpdate() {
		return this.lastUpdate;	}
	public void setUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;	}

    
}
