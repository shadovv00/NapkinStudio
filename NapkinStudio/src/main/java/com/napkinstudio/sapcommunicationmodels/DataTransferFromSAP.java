package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.util.*;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DataTransferFromSAP")
public class DataTransferFromSAP {
//	private String field;
	private UsersFromSAP users;
	private Orders orders;
	private CommentsFromSAP commentaries;
	private UserOrdersFromSAP userOrdersConnection;
    private StatusChangesFromSAP statusChangeHistory;
	private AttachmentsFromSAP attachments;
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

    public StatusChangesFromSAP getSapStatusChanges() {return statusChangeHistory;}
    public void setSapStatusChanges(StatusChangesFromSAP statusChangeHistory) {this.statusChangeHistory = statusChangeHistory;}

	public AttachmentsFromSAP getSapAttachments() {return attachments;}
	public void setSapAttachments(AttachmentsFromSAP attachments) {this.attachments = attachments;}


}



