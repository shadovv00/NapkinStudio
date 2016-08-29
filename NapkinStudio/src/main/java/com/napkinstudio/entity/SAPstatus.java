package com.napkinstudio.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User1 on 29.07.2016.
 */

@NamedQueries({

        /*@NamedQuery(name = "Status.findByRoleId", query = "SELECT s FROM  Status s  inner join s.roles r WHERE r.id  =:id"), */})
@Entity
@XStreamAlias("sapStatus")
@Table(name="SAPstatus")
public class SAPstatus {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "SAPStatus",
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<StatusSAPStatusRole> statusSAPStatuseRoles;

    public List<StatusSAPStatusRole> getUserGroups() {
        return statusSAPStatuseRoles;
    }

    public void setStatusSAPStatuseRoles(List<StatusSAPStatusRole> statusSAPStatuseRoles) {
        this.statusSAPStatuseRoles = statusSAPStatuseRoles;
    }

    public List<StatusSAPStatusRole> getStatusSAPStatuseRoles() {
        return statusSAPStatuseRoles;
    }
    //    @ManyToMany(mappedBy = "sapStatuses")
//    private List<Status> statuses;

//    public List<Status> getStatuses() {
//        return statuses;    }
//    public void setStatuses(List<Status> statuses) {
//        this.statuses = statuses;    }

    public Integer getId() {
        return id;    }
    public void setId(Integer id) {
        this.id = id;    }


    public String getName() {
        return name;    }
    public void setName(String name) {
        this.name = name;    }

    
}
