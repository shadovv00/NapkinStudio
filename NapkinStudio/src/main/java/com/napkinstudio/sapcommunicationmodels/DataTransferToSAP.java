package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.User;
import com.napkinstudio.util.Orders;
import com.napkinstudio.util.OrdersToSAP;
import com.napkinstudio.util.UsersFromSAP;
import com.napkinstudio.util.UsersToSAP;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;

@XStreamAlias("DataTransferToSAP")
public class DataTransferToSAP {

	private UsersToSAP users;
	private OrdersToSAP orders;

	public OrdersToSAP getSapOrders() {return orders;}
	public void setSapOrders(OrdersToSAP orders) {this.orders = orders;}

	public UsersToSAP getSapUsers() {return users;}
	public void setSapUsers(UsersToSAP users) {this.users = users;}


//	@XStreamImplicit
//	public LinkedList<Order> order;
//	public LinkedList<Order> getOrders() {
//		return order;}
//	public void setOrders(LinkedList<Order> order) {
//		this.order = order;}

//	private UsersFromSAP users;
//	private Orders orders;
//
//	public Orders getSapOrders() {return orders;}
//	public void setSapOrders(Orders orders) {this.orders = orders;}
//
//	public UsersFromSAP getSapUsers() {return users;}
//	public void setSapUsers(UsersFromSAP users) {this.users = users;}
}
