package com.napkinstudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: StatusChange
 *
 */
@Entity
@Table(name="status_changes")

public class StatusChange implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer Id;
	
    @ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
	private Order order;
	public Order getOrder() {
		return this.order;	}
	public void setOrder(Order order) {
		this.order = order;	}   

	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})	
	private SAPstatus sapStatus;
	private Date dateTime;
	private static final long serialVersionUID = 1L;

	public StatusChange() {
		super();
	}   
	public Integer getId() {
		return this.Id;
	}

	public void setId(Integer Id) {
		this.Id = Id;
	}   
	public SAPstatus getSAPstatus() {
		return this.sapStatus;
	}

	public void setSAPstatus(SAPstatus sapStatus) {
		this.sapStatus = sapStatus;
	}   
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
   
}
