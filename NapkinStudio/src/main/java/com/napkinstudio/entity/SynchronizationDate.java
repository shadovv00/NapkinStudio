package com.napkinstudio.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "synchro_date")
public class SynchronizationDate {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    public Integer getId() {
        return this.id;	}
    public void setId(Integer id) {
        this.id = id;	}


    private Date dateFromSAP;
    private Date dateToSAP;
    private Boolean errorFromSAP;
    private Boolean errorToSAP;

    public Date getDateFromSAP() {
        return this.dateFromSAP;	}
    public void setDateFromSAP(Date dateFromSAP) {
        this.dateFromSAP = dateFromSAP;	}

    public Date getDateToSAP() {
        return this.dateToSAP;	}
    public void setDateToSAP(Date dateToSAP) {
        this.dateToSAP = dateToSAP;	}

    public Boolean getErrorFromSAP() {
        return errorFromSAP;    }
    public void setErrorFromSAP(Boolean errorFromSAP) {
        this.errorFromSAP = errorFromSAP;    }

    public Boolean getErrorToSAP() {
        return errorToSAP;    }
    public void setErrorToSAP(Boolean errorToSAP) {
        this.errorToSAP = errorToSAP;    }


}
