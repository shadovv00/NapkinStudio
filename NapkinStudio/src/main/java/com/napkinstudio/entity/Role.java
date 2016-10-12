package com.napkinstudio.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Created by User1 on 20.07.2016.
 */



//@NamedNativeQuery(name = "Role.findCommentsByOrderId",
//
//        query = "select * from role as r inner join comments  as c on c.forRole_id = r.id"
////                "        LEFT JOIN" +
////                "    (SELECT " +
////                "        c.commText as comtext, c.forRole_id" +
////                "    FROM" +
////                "        comments AS c" +
////                "    INNER JOIN orders AS o ON o.orderId = c.order_orderId" +
////                "    WHERE" +
////                "    o.orderId =: id) comm ON r.id = comm.forRole_id"
//
//)

@NamedQueries({

            //to display comments through roles
//     @NamedQuery(name="Role.findCommentsByOrderId",query = "Select role From Role  role left join role.comments  c as (select comments from Comments comments inner join comments.order order where order.orderId = 1402130001)"),
        @NamedQuery(name = "Role.findByUserId", query = "SELECT r FROM  Role r  inner join r.users u WHERE u.userId  =:id"), })

@Entity
@XStreamAlias("role")
@Table(name="Role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    @OneToMany(mappedBy = "forRole")
    private List<Comments> comments;

    @OneToMany(mappedBy = "role",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<StatusSAPStatusRole> statusSAPStatuseRoles;

    public Set<StatusSAPStatusRole> getUserGroups() {
        return statusSAPStatuseRoles;
    }

    public Set<StatusSAPStatusRole> getStatusSAPStatuseRoles() {
        return statusSAPStatuseRoles;
    }

    @OneToMany(mappedBy = "role",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ProgresBarFields> progresBarFieldses;
    public Set<ProgresBarFields> getProgresBarFieldses() {
        return progresBarFieldses;
    }
    public void setProgresBarFieldses(Set<ProgresBarFields> progresBarFieldses) {
        this.progresBarFieldses = progresBarFieldses;
    }


//    @ManyToMany(fetch = FetchType.EAGER,cascade = {MERGE,REMOVE})
//    private List<Status> status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


//    public List<Status> getStatus() {
//        return status;
//    }
//
//    public void setStatus(List<Status> status) {
//        this.status = status;
//    }
}
