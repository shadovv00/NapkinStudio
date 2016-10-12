package com.napkinstudio.entity;

import javax.persistence.*;

/**
 * Created by User1 on 25.08.2016.
 */

@NamedQueries(
        @NamedQuery(name = "StatusSAPStatusRole.findStatusByRoleIdAndSAPStatusId",query = "select s from StatusSAPStatusRole s " +
                "inner join s.role r " +
                "inner join s.SAPStatus ss where r.id =:roleId and ss.id =:sapStatusId ")
)
@Entity
@Table(name = "STATUS_SAPSTATUS_ROLE")

public class StatusSAPStatusRole {
//    private StatusSAPStatusRoleID primaryKey = new StatusSAPStatusRoleID();

//    @EmbeddedId
//    public StatusSAPStatusRoleID getPrimaryKey() {
//        return primaryKey;
//    }
//
//    public void setPrimaryKey(StatusSAPStatusRoleID primaryKey) {
//        this.primaryKey = primaryKey;
//    }
//
//        @Transient
//        public Role getRole() {
//            return getPrimaryKey().getRole();
//        }
//
//        public void setRole(Role role) {
//            getPrimaryKey().setRole(role);
//        }
//
//        @Transient
//        public Status getStatus() {
//            return getPrimaryKey().getStatus();
//        }
//
//        public void setStatus(Status status) {
//            this.getPrimaryKey().setStatus(status);
//        }
//        @Transient
//        public SAPstatus getSAPStatus() {
//            return getPrimaryKey().getSAPStatus();
//        }
//
//        public void setSAPStatus(SAPstatus SAPStatus) {
//           getPrimaryKey().setSAPStatus(SAPStatus);
//        }

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "STATUS_ID")
    private Status status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "SAPSTATUS_ID")
    private SAPstatus SAPStatus;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "ROLE_ID")
    private Role role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public SAPstatus getSAPStatus() {
        return SAPStatus;
    }

    public void setSAPStatus(SAPstatus SAPStatus) {
        this.SAPStatus = SAPStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

