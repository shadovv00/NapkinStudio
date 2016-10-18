package com.napkinstudio.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;


@XStreamAlias("orders")
public class OrdersToSAP {
        @XStreamImplicit
        public LinkedList<OrderToSAP> order;



        public LinkedList<OrderToSAP> getOrders() {
            return order;
        }

        public void setOrders(LinkedList<OrderToSAP> order) {
            this.order = order;
        }

    }
