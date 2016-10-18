package com.napkinstudio.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import static javax.persistence.CascadeType.MERGE;

import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Comments
 *
 */

@NamedQueries({
		@NamedQuery(name = "Comments.findCommentsbyOrderId", query = "select c from Comments c inner join c.order o where o.orderId =:id order by c.lastModifiedDate desc "),
		@NamedQuery(name = "Comments.findCommentsByOrderAndRoleId", query = "select c from Comments c inner join c.order o inner join c.forRole r where o.orderId =:orderId and r.id =:roleId  order by c.lastModifiedDate desc "),
        @NamedQuery(name="Comments.findCommentsbyOrderAndRoleIDs", query = "select c from Comments c inner join c.order o inner join c.forRole r where o.orderId =:orderId and r.id  in (:roleIdList) order by c.lastModifiedDate desc "),
		@NamedQuery(name = "Comments.countAllUnreadComments", query = "select count(c) from Comments c " +
				"inner join c.order o inner join o.itsUsers iu inner join iu.user u where o.orderId=:orderId and u.userId =:userId and c.lastModifiedDate > iu.lastLook"),
		@NamedQuery(name = "Comments.countUnreadCommentsByRoleId", query = "select count(c) from Comments c " +
				"inner join c.forRole r inner join c.order o inner join o.itsUsers iu inner join iu.user u where o.orderId=:orderId and u.userId =:userId and c.lastModifiedDate > iu.lastLook" +
				" and r.id =:roleId"),
        @NamedQuery(name = "Comments.countUnreadCommentsByRoleIds", query = "select count(c) from Comments c " +
                "inner join c.forRole r inner join c.order o inner join o.itsUsers iu inner join iu.user u where o.orderId=:orderId and u.userId =:userId and c.lastModifiedDate > iu.lastLook" +
                " and r.id in (:roleIdList)"),
}
)
@Entity
//@XStreamAlias("comment")

@Table(name = "comments")
public class Comments extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer Id;

	@ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE})
	private User fromUser;

//	@ManyToOne(fetch = FetchType.LAZY,cascade = {MERGE})
	@Transient
	private User toUser;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE})
	private Order order;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {MERGE})
	private Role forRole;

	@Transient
	private Boolean unread;

	@Column(name = "commText",columnDefinition="TEXT")
	private String commText;

	private Boolean deleted;

	private Date dateTime;

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


	public Boolean getDeleted() {
		return deleted;    }
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;    }

	public Boolean getUnread() {
		return unread;
	}

	public void setUnread(Boolean unread) {
		this.unread = unread;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
