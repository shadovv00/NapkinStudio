package com.napkinstudio.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Comments
 *
 */

@NamedQueries(
		@NamedQuery(name = "Comments.findCommentsbyOrderId", query = "select c from Comments c inner join c.order o where o.orderId =:id")
)
@Entity

@Table(name = "comments")
public class Comments implements Serializable {

	   
	@Id
	@GeneratedValue
	private Integer Id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
	private User fromUser;
    
	@ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
	private User toUser;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE,REMOVE})
	private Order order;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE,REMOVE})
	private Role forRole;
    
    
	private String commText;
	private Date dateTime;
	private Boolean deleted;
	private static final long serialVersionUID = 1L;

	public Comments() {
		super();	}   
	public Integer getId() {
		return this.Id;	}
	public void setId(Integer Id) {
		this.Id = Id;	}   
	
	public User getFromUser() {
		return this.fromUser;	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;	}  
	
	public User getToUser() {
		return this.toUser;	}
	public void setToUser(User toUser) {
		this.toUser = toUser;	}  
	
	public Order getOrder() {
		return this.order;	}
	public void setOrder(Order order) {
		this.order = order;	}   
	
	public Role getForRole() {
		return this.forRole;	}
	public void setForRole(Role forRole) {
		this.forRole = forRole;	}   
	
	public String getCommText() {
		return this.commText;	}
	public void setCommText(String commText) {
		this.commText = commText;	}   

	public Date getDateTime() {
		return this.dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getDeleted() {
		return deleted;    }
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;    }



}
