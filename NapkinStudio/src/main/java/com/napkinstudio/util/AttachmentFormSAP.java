package com.napkinstudio.util;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("attachmentFromSAP")
public class AttachmentFormSAP {
    private Integer roleId;
    private Integer orderId;
    private String name;
    private Date appendDate;

    public Integer getOrder() {
        return this.orderId;	}
    public void setOrder(Integer orderId) {
        this.orderId = orderId;	}

    public Integer getRole() {
        return this.roleId;	}
    public void setRole(Integer roleId) {
        this.roleId = roleId;	}

    public String getName() {
        return this.name;	}
    public void setName(String name) {
        this.name = name;	}

    public Date getAppendDate() {
        return this.appendDate;    }
    public void setAppendDate(Date appendDate) {
        this.appendDate = appendDate;    }



}
