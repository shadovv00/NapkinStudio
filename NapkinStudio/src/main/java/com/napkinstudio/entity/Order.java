package com.napkinstudio.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

@Table(name = "orders")
@XStreamAlias("order")
public class Order {

 
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String debNum;
    private String debOrderNum;
    private String quantity;
    private String approvalBy;
    private String debCont;
    private String delivAddr;
    private String delivAddrCont;
    private String delivInstruct;
    private String unloadTimes;

    private String itemNum;
    private String debItemNum;
    private String size;
    private String material;
    private String foldingMeth;
    private String napkinCol;
    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private String version;

    private String pVIcheckScen;
    private String debCheckScen;
    private Boolean toDeptor;
    private Boolean deleted;
    private Date lastUpdate;
    
    
//    @OneToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
//    private Article article;
//    public Article getArticle() {
//        return article;    }
//    public void setArticle(Article article) {
//        this.article = article;    }

    @ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private Customer customer;
    public Customer getCustomer() {
        return customer;    }
    public void setCustomer(Customer customer) {
        this.customer = customer;    }
       
//    @ManyToMany(mappedBy="orders",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
//    private List<User> users;
//    public List<User> getUsers() {
//        return users;    }
//    public void setUsers(List<User> users) {
//        this.users = users;    }
    @OneToMany(mappedBy="order",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<UserOrder> itsUsers;
    public List<UserOrder> getItsUsers() {
        return itsUsers;    }
    public void setItsUsers(List<UserOrder> itsUsers) {
        this.itsUsers = itsUsers;    }


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

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE,REMOVE})
    private SAPstatus sapStatus;
    public SAPstatus getSAPstatus() {
        return sapStatus;    }
    public void setSAPstatus(SAPstatus sapStatus) {
        this.sapStatus = sapStatus;    }


    public Integer getOrderId() {
        return orderId;    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;    }

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



    public String getItemNum() {
        return itemNum;    }
    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;    }

    public String getDebItemNum() {
        return debItemNum;    }
    public void setDebItemNum(String debItemNum) {
        this.debItemNum = debItemNum;    }

    public String getSize() {
        return size;    }
    public void setSize(String size) {
        this.size = size;    }

    public String getMaterial() {
        return material;    }
    public void setMaterial(String material) {
        this.material = material;    }

    public String getFoldingMeth() {
        return foldingMeth;    }
    public void setFoldingMeth(String foldingMeth) {
        this.foldingMeth = foldingMeth;    }

    public String getNapkinCol() {
        return napkinCol;    }
    public void setNapkinCol(String napkinCol) {
        this.napkinCol = napkinCol;    }

    public String getColor1() {
        return color1;    }
    public void setColor1(String color1) {
        this.color1 = color1;    }

    public String getColor2() {
        return color2;    }
    public void setColor2(String color2) {
        this.color2 = color2;    }

    public String getColor3() {
        return color3;    }
    public void setColor3(String color3) {
        this.color3 = color3;    }

    public String getColor4() {
        return color4;    }
    public void setColor4(String color4) {
        this.color4 = color4;    }

    public String getVersion() {
        return version;    }
    public void setVersion(String version) {
        this.version = version;    }




    public String getPVIcheckScen() {
        return pVIcheckScen;    }   
    public void setPVIcheckScen(String pVIcheckScen) {
        this.pVIcheckScen = pVIcheckScen;    }

    public String getDebCheckScen() {
        return debCheckScen;    }   
    public void setDebCheckScen(String debCheckScen) {
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
