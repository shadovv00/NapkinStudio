package com.napkinstudio.util;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.User;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("users")
public class UsersFromSAP {
    @XStreamImplicit
    public LinkedList<User> user;



    public LinkedList<User> getUsers() {
        return user;
    }

    public void setUsers(LinkedList<User> order) {
        this.user = order;
    }


}