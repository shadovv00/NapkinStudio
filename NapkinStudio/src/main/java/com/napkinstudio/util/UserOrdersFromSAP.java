package com.napkinstudio.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.LinkedList;

@XStreamAlias("userOrdersConnection")
public class UserOrdersFromSAP {
    @XStreamImplicit
    public LinkedList<UserOrderFromSAP> userOrderFromSAP;



    public LinkedList<UserOrderFromSAP> getUserOrders() {
        return userOrderFromSAP;
    }

    public void setUserOrders(LinkedList<UserOrderFromSAP> userOrderFromSAP) {
        this.userOrderFromSAP = userOrderFromSAP;
    }

}
