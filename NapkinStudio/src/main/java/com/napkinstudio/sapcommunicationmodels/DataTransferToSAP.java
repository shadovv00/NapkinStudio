package com.napkinstudio.sapcommunicationmodels;

import com.napkinstudio.entity.User;

public class DataTransferToSAP {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DataTransferToSAP [user=" + user + "]";
	}

}
