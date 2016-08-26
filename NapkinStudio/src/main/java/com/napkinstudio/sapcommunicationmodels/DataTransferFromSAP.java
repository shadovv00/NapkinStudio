package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.entity.Orders;
import java.util.ArrayList;
import java.util.List;

import com.napkinstudio.entity.Order;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("DataTransferFromSAP")
public class DataTransferFromSAP {
//	private String field;
	private Orders orders;
//	@XStreamImplicit
//	private List<Order> order;


//	public String getField() {
//		return field;
//	}

//	public void setField(String field) {
//		this.field = field;
//	}

	public Orders getSapOrders() {
		return orders;
	}

	public void setSapOrders(Orders orders) {
		this.orders = orders;
	}

//	public List<Order> getOrders() {
//		return order;
//	}
//
//	public void setOrders(List<Order> order) {
//		this.order = order;
//	}



//	@Override
//	public String toString() {
//		return "DataTransferStore [field=" + order + "]";
//	}

}



