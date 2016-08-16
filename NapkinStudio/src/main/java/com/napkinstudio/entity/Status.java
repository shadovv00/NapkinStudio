package com.napkinstudio.entity;

import javax.persistence.*;
import java.util.List;
import com.napkinstudio.entity.Role;
import com.napkinstudio.entity.SAPstatus;

/**
 * Created by User1 on 29.07.2016.
 */

@NamedQueries({

       /* @NamedQuery(name = "Status.findByRoleId", query = "SELECT s FROM  Status s  inner join s.roles r WHERE r.id  =:id"),*/ })
@Entity
@Table(name="Status")
public class Status {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "status")
    private List<Role> roles;

    @ManyToMany(mappedBy = "statuses")
    private List<SAPstatus> sapStatuses;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public List<SAPstatus> getSAPstatuses() {
        return sapStatuses;
    }

    public void setSAPstatuses(List<SAPstatus> sapStatuses) {
        this.sapStatuses = sapStatuses;
    }
}
