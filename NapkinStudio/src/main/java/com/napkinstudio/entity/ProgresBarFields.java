package com.napkinstudio.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

//@NamedQueries(
@NamedNativeQuery(name = "ProgresBarFields.findBarByRolePVICheckReject",
//                query = "SELECT s.name as name, MAX(tm.lastdate) as date " +
//                        "FROM progresbar_fields AS pbf " +
//                        "INNER JOIN role AS r ON pbf.ROLE_ID = r.id " +
//                        "INNER JOIN status AS s ON s.id = pbf.STATUS_ID " +
//                        "INNER JOIN sapstatus ss ON ss.id = pbf.SAPSTATUS_ID " +
//                        "LEFT JOIN " +
//                            "(SELECT sc.sapStatus_id AS reid, sc.dateTime AS lastdate " +
//                            "FROM status_changes AS sc " +
//                            "INNER JOIN orders AS o ON o.orderId = sc.order_orderId " +
//                            "WHERE o.orderId =:orderId " +
//                            ") tm ON ss.id = tm.reid " +
//                        "WHERE r.id = :roleId AND pbf.pVIcheckScen =:pVIcheckScen AND pbf.rejected =:rejected " +
//                        "GROUP BY s.name order by pbf.id "
query = "SELECT s.name as name, MAX(tm.lastdate) as date " +
        "FROM progresbar_fields AS pbf " +
        "INNER JOIN Role AS r ON pbf.ROLE_ID = r.id " +
        "INNER JOIN Status AS s ON s.id = pbf.STATUS_ID " +
        "INNER JOIN SAPstatus ss ON ss.id = pbf.SAPSTATUS_ID " +
        "LEFT JOIN " +
            "(SELECT sc.sapStatus_id AS reid, sc.dateTime AS lastdate " +
            "FROM status_changes AS sc " +
            "INNER JOIN orders AS o ON o.orderId = sc.order_orderId " +
            "WHERE o.orderId =:orderId " +
            ") tm ON ss.id = tm.reid " +
        "WHERE r.id = :roleId AND pbf.pVIcheckScen =:pVIcheckScen AND pbf.rejected =:rejected " +
        "GROUP BY s.name order by pbf.id "

//        , resultClass = Object[].class



        )



@Entity
@Table(name = "progresbar_fields")
public class ProgresBarFields {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    private String name;
    private Boolean rejected;
    private Boolean pVIcheckScen;


    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "SAPSTATUS_ID")
    private SAPstatus SAPStatus;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "STATUS_ID")
    private Status status;




    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getRejected() {
        return rejected;    }
    public void setRejected(Boolean rejected) {
        this.rejected = rejected;    }

    public Boolean getPVIcheckScen() {
        return pVIcheckScen;    }
    public void setPVIcheckScen(Boolean pVIcheckScen) {
        this.pVIcheckScen = pVIcheckScen;    }

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

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }


}
