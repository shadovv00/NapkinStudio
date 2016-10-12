package com.napkinstudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;


@NamedQueries({
	@NamedQuery(name = "Attachment.findAttachmentsbyRoleId", query = "select a from Attachment a join a.role r where r.id =:id"),
	@NamedQuery(name = "Attachment.findAttachmentMapByOrderIdRoleId", query = "select a from Attachment a join a.role r join a.order o where o.orderId =:orderId and r.id =:roleId"),
	@NamedQuery(name = "Attachment.findAttachmentMapByOrderId", query = "select a from Attachment a join a.order o where o.orderId =:orderId"),
	@NamedQuery(name = "Attachment.deleteByName", query = "delete from Attachment a where a.name = :name")
})


@Entity
@Table(name = "attachment")
public class Attachment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String name;
//	private String extension;
	private Date appendDate;

	
	@ManyToOne
    @JoinColumn(name = "ROLE_ID")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	
	
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

//	public String getExtension() {
//		return extension;
//	}
//
//	public void setExtension(String extension) {
//		this.extension = extension;
//	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getAppendDate() {
		return this.appendDate;    }
	public void setAppendDate(Date appendDate) {
		this.appendDate = appendDate;    }



}
