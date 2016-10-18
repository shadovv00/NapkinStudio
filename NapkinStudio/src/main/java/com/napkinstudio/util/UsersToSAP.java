package com.napkinstudio.util;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;

@XStreamAlias("users")
public class UsersToSAP {
        @XStreamImplicit
        public LinkedList<UserToSAP> user;

        public LinkedList<UserToSAP> getUsers() {
            return user;
        }

        public void setUsers(LinkedList<UserToSAP> user) {
            this.user = user;
        }



    }
