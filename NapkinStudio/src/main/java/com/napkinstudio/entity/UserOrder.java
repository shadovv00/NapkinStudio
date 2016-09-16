package com.napkinstudio.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;


@NamedQueries({
        @NamedQuery(name="UserOrder.findOrdersByUserId",query = "SELECT userOrder from UserOrder userOrder  inner join userOrder.user user where user.id =:id"),
        @NamedQuery(name="UserOrder.findUserOrdersByOrderId",query = "SELECT userOrder from UserOrder userOrder  inner join userOrder.order o where o.orderId =:id"),
        @NamedQuery(name="UserOrder.findUserforOrdedByRole", query = "SELECT uo.user FROM UserOrder uo inner join uo.order o inner join uo.user u inner join u.roles r where o.orderId =:orderId and r.id =:roleId")
})

@Entity
@XStreamAlias("itsUsers")

@Table(name = "users_orders")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Date lastLook;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE,REMOVE})
    private Order order;
    public Order getOrder() {
        return this.order;	}
    public void setOrder(Order order) {
        this.order = order;	}

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE,REMOVE})
    private User user;
    public User getUser() {
        return this.user;	}
    public void setUser(User user) {
        this.user = user;	}


    public Date getLastLook() {
        return this.lastLook;
    }
    public void setLastLook(Date lastLook) {
        this.lastLook = lastLook;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
