package com.napkinstudio.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.napkinstudio.entity.Order;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("orders")
public class Orders {
    @XStreamImplicit
    public LinkedList<Order> order;



    public LinkedList<Order> getOrders() {
		return order;
	}

	public void setOrders(LinkedList<Order> order) {
		this.order = order;
	}


}
