package com.napkinstudio.util;


import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

@XStreamAlias("statusChangeFromSAP")
public class StatusChangeFromSAP {

    private Integer orderId;
    private Integer sapStatusId;
    private Date dateTime;


    public Integer getOrder() {
        return this.orderId;	}
    public void setOrder(Integer orderId) {
        this.orderId = orderId;	}

    public Integer getSAPstatus() {
        return this.sapStatusId;}
    public void setSAPstatus(Integer sapStatus) {
        this.sapStatusId = sapStatusId;}

    public Date getDateTime() {
        return this.dateTime;}
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;}

}
