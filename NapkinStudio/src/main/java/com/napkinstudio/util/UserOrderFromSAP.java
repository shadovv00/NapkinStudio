package com.napkinstudio.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("userOrderFromSAP")
public class UserOrderFromSAP {


    private Integer orderId;
    private Integer userId;
    private Date lastLook;

    public Integer getOrder() {
        return this.orderId;	}
    public void setOrder(Integer orderId) {
        this.orderId = orderId;	}

    public Integer getUser() {
        return this.userId;	}
    public void setUser(Integer userId) {
        this.userId = userId;	}

    public Date getLastLook() {
        return this.lastLook;    }
    public void setLastLook(Date lastLook) {
        this.lastLook = lastLook;    }




}
