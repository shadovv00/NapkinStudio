package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.util.CommentsFromSAP;
import com.napkinstudio.util.Orders;

import com.napkinstudio.util.UserOrdersFromSAP;
import com.napkinstudio.util.UsersFromSAP;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DataTransferFromSAP")
public class DataTransferFromSAP {
//	private String field;
	private UsersFromSAP users;
	private Orders orders;
	private CommentsFromSAP commentaries;
	private UserOrdersFromSAP userOrdersConnection;
//	@XStreamImplicit
//	private List<Order> order;



	public Orders getSapOrders() {return orders;}
	public void setSapOrders(Orders orders) {this.orders = orders;}

	public UsersFromSAP getSapUsers() {return users;}
	public void setSapUsers(UsersFromSAP users) {this.users = users;}

	public CommentsFromSAP getSapComments() {return commentaries;}
	public void setSapComments(CommentsFromSAP commentaries) {this.commentaries = commentaries;}

	public UserOrdersFromSAP getSapUserOrders() {return userOrdersConnection;}
	public void setSapUserOrders(UserOrdersFromSAP userOrdersConnection) {this.userOrdersConnection = userOrdersConnection;}


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



