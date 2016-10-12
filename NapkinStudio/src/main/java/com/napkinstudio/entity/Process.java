package com.napkinstudio.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "process")

public class Process {
    @Id
    private  byte processId;
    private String processName;

//    TODO: Connect to Order, Create dao and manager, SetUp in InitDB

    public byte getProcessId() {
        return processId;    }
    public void setProcessId(byte processId) {
        this.processId = processId;    }

    public String getProcessName() {
        return processName;    }
    public void setProcessName(String processName) {
        this.processName = processName;    }


}
