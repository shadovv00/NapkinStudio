package com.napkinstudio.entity;

import com.napkinstudio.util.Listener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by User1 on 20.07.2016.
 */
@MappedSuperclass
@EntityListeners(Listener.class)
public class AbstractEntity {
    @Column(name = "LastModifiedDate")
    private Date lastModifiedDate;

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}