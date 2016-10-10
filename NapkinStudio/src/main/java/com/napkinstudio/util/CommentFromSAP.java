package com.napkinstudio.util;

import com.napkinstudio.entity.AbstractEntity;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.User;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import static javax.persistence.CascadeType.MERGE;

import java.util.Date;

import javax.persistence.*;




@XStreamAlias("commentFromSAP")
public class CommentFromSAP extends AbstractEntity {


    @javax.persistence.Id
    @GeneratedValue
    private Integer fromUserId;
    private Integer toUserId;
    private Integer orderId;
    private Integer forRoleId;
    private String commText;
    private Date dateTime;
    private Boolean deleted;

    private static final long serialVersionUID = 1L;

    public Integer getFromUser() {
        return this.fromUserId;	}
    public void setFromUser(Integer fromUserId) {
        this.fromUserId = fromUserId;	}

    public Integer getToUser() {
        return this.toUserId;	}
    public void setToUser(Integer toUserId) {
        this.toUserId = toUserId;	}

    public Integer getOrder() {
        return this.orderId;	}
    public void setOrder(Integer orderId) {
        this.orderId = orderId;	}

    public Integer getForRole() {
        return this.forRoleId;	}
    public void setForRole(Integer forRoleId) {
        this.forRoleId = forRoleId;	}

    public String getCommText() {
        return this.commText;	}
    public void setCommText(String commText) {
        this.commText = commText;	}

    public Date getDateTime() {
        return this.dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getDeleted() {
        return deleted;    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;    }

}
