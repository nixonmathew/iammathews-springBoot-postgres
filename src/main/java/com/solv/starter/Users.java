package com.solv.starter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue
	@Column(name="name")
	private String name;
	
	@Column(name="mobile")
	private String mobile ;
	
	@Column(name="address")
	private String address ;

	public Users(String name, String mobile, String address) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.address = address;
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
