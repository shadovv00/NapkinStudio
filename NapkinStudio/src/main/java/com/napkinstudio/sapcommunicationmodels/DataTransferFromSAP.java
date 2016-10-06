package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.util.Orders;

import com.napkinstudio.util.UsersFromSAP;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DataTransferFromSAP")
public class DataTransferFromSAP {
//	private String field;
	private Orders orders;
	private UsersFromSAP usersFromSAP;
//	@XStreamImplicit
//	private List<Order> order;



	public Orders getSapOrders() {return orders;}
	public void setSapOrders(Orders orders) {this.orders = orders;}

	public UsersFromSAP getSapUsers() {return usersFromSAP;}
	public void setSapUsers(UsersFromSAP orders) {this.usersFromSAP = usersFromSAP;}



//	public String getField() {
//		return field;
//	}
//	public void setField(String field) {
//		this.field = field;
//	}
//	public List<Order> getOrders() {
//		return order;
//	}
//	public void setOrders(List<Order> order) {
//		this.order = order;
//	}



//	@Override
//	public String toString() {
//		return "DataTransferStore [field=" + order + "]";
//	}

}



