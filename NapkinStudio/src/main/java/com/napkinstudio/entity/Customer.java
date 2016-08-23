package com.napkinstudio.entity;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import com.napkinstudio.entity.Order;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Customer
 *
 */
@Entity
@Table(name = "customers")

public class Customer implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String Name;
	private String Address;
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}   

    @OneToMany(mappedBy="customer",fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
    private List<Order> orders;
    public List<Order> getOrders() {
        return orders;    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;    }
	
	
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}   
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}   
	public String getAddress() {
		return this.Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}
   
}
