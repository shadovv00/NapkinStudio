package com.napkinstudio.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

@NamedQueries({



//    @NamedQuery(name = "User.findAllByFirstName", query = "SELECT u FROM  User  u  WHERE u.firstName =:firstName"),
//    @NamedQuery(name = "User.findAllByLastName", query = "SELECT u FROM  User  u  WHERE u.lastName  =:lastName"),
    @NamedQuery(name = "PersistentLogin.findByUserName", query = "SELECT u FROM  PersistentLogin  u  WHERE u.username   =:username"),
//   @NamedQuery(name = "User.deleteById", query = "DELETE FROM User u WHERE u.userId = ?1"),
    /*@NamedQuery(name = "User.deactivateById", query = "update User as u set u.enabled =0  where u.userId = ?1"),
    @NamedQuery(name = "User.activateById", query = "update User as u set u.enabled =1  where u.userId = ?1"),*/ })


@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable{

	@Id
	private String series;

	@Column(name="USERNAME", unique=true, nullable=false)
	private String username;
	
	@Column(name="TOKEN", unique=true, nullable=false)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}
	
	
}
