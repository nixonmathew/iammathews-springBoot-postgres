package com.self.portfolio.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "user_auth")
public class UsersAuth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password ;

//	@OneToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY,  mappedBy = "user_auth")
//	private Users users;

	public UsersAuth() {
		super();
	}


	public UsersAuth( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "UsersAuth [id=" + id + ", username=" + username + ", password=" + password + "]";
	}


	public void setPassword(String password) {
		this.password = password;
	}
//
//	public Users getUsers() {
//		return users;
//	}
//
//	public void setUsers(Users users) {
//		this.users = users;
//	}


}
