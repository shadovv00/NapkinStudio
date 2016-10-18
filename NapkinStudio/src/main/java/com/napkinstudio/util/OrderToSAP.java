package com.napkinstudio.util;


import com.napkinstudio.entity.*;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;


@XStreamAlias("order")
public class OrderToSAP extends AbstractEntity {

    private Integer orderId;
    private String version;
    private Boolean repeated;
    private Boolean deleted;
    private Integer sapStatusId;


    public Integer getOrderId() {
        return orderId;    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;    }


    public String getVersion() {
        return version;    }
    public void setVersion(String version) {
        this.version = version;    }

    public Boolean getRepeated() {
        return repeated;    }
    public void setRepeated(Boolean repeated) {
        this.repeated = repeated;    }

    public Boolean getDeleted() {
        return deleted;    }
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;    }

    public Integer getSAPstatus() {
        return sapStatusId;    }
    public void setSAPstatus(Integer sapStatusId) {
        this.sapStatusId = sapStatusId;    }


}
