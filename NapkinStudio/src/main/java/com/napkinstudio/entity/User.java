package com.napkinstudio.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CacheStoreMode.REFRESH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;
import java.util.Date;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.Comments;;

/**
 * Created by User1 on 20.07.2016.
 */
@Entity

@NamedQueries({



        @NamedQuery(name = "User.findAllByFirstName", query = "SELECT u FROM  User  u  WHERE u.firstName =:firstName"),
        @NamedQuery(name = "User.findAllByLastName", query = "SELECT u FROM  User  u  WHERE u.lastName  =:lastName"),

        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM  User  u  WHERE u.login   =:login"),


        @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.userId = ?1"),
        /*@NamedQuery(name = "User.deactivateById", query = "update User as u set u.enabled =0  where u.userId = ?1"),
        @NamedQuery(name = "User.activateById", query = "update User as u set u.enabled =1  where u.userId = ?1"),*/ })

@Table(name = "USER")
public class User extends AbstractEntity {

 
	/**
     * User identification number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Boolean enabled;
    private Date lastSession;
    
    @ManyToMany(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    @JoinTable
    private List<Role> roles;
    public List<Role> getRoles() {
        return roles;    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    @JoinTable
//  @JoinTable(name="ORDER_DETAIL",
//  joinColumns=
//  @JoinColumn(name="ORDER_ID", referencedColumnName="ORDER_ID"),
//inverseJoinColumns=
//  @JoinColumn(name="PROD_ID", referencedColumnName="PROD_ID")
//)
    private List<Order> orders;
    public List<Order> getOrders() {
        return orders;    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;    }

    
    
    
    
    @OneToMany(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<Comments> myComments;
    public List<Comments> getMyComments() {
        return myComments;    }
    public void setMyComments(List<Comments> myComments) {
        this.myComments = myComments;    }
    
    
    
    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;    }
    public void setEmail(String email) {
        this.email = email;    }

	public Date getDateTime() {
		return this.lastSession;	}
	public void setDateTime(Date lastSession) {
		this.lastSession = lastSession;	}

    
}
