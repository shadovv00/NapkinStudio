package com.napkinstudio.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by User1 on 29.07.2016.
 */

@NamedQueries({

        /*@NamedQuery(name = "Status.findByRoleId", query = "SELECT s FROM  Status s  inner join s.roles r WHERE r.id  =:id"), */})
@Entity
@Table(name="SAPstatus")
public class SAPstatus {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "sapStatuses")
    private List<Status> statuses;

    public List<Status> getStatuses() {
        return statuses;    }
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;    }

    public Integer getId() {
        return id;    }
    public void setId(Integer id) {
        this.id = id;    }


    public String getName() {
        return name;    }
    public void setName(String name) {
        this.name = name;    }

    
}
