package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;

@XStreamAlias("DataTransferToSAP")
public class DataTransferToSAP {

	@XStreamImplicit
	public LinkedList<Order> order;

	public LinkedList<Order> getOrders() {
		return order;
	}

	public void setOrders(LinkedList<Order> order) {
		this.order = order;
	}




//	private User user;
//
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	@Override
//	public String toString() {
//		return "DataTransferToSAP [user=" + user + "]";
//	}

}
