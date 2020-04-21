package com.solv.starter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private int id;

	@Column(name="name")
	private String name;

	@Column(name="mobile")
	private String mobile ;

	@Column(name="address")
	private String address ;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_auth_id")
	private UsersAuth user_auth ;
	
	public UsersAuth getUser_auth() {
		return user_auth;
	}


	public void setUser_auth(UsersAuth user_auth) {
		this.user_auth = user_auth;
	}


	public Users() {
		super();
	}


	public Users(String name, String mobile, String address) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", mobile=" + mobile + ", address=" + address + ", user_auth="
				+ user_auth + ",test =  ," + user_auth.toString()  + "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}




}