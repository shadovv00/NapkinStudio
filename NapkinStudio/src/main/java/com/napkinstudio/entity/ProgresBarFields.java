package com.napkinstudio.entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@NamedQueries(
        @NamedQuery(name = "ProgresBarFields.findBarByRolePVICheckReject",
                    query = "select s.name, ss.name  from ProgresBarFields pbf " +
                            "inner join pbf.role r " +
                            "inner join pbf.status s " +
                            "inner join pbf.SAPStatus ss "+
                            "inner join pbf.SAPStatus ss "+
                            "left join ss.statusChanges sc "+

                                "where r.id =:roleId "+
                                    " and pbf.pVIcheckScen =:pVIcheckScen and pbf.rejected =:rejected ")
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
