package com.infosys.spa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="users")
public class User {

	@Id //Primary Key
	@Column(name="user_id") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="user_name")
	private String userName;
	@Column(name="user_address")
	private String userAddress;
	@Column(name="user_salary")
	private int userSalary;
	
	public User() {
		super();
	}

	public User(String userName, String userAddress, int userSalary) {
		super();
		this.userName = userName;
		this.userAddress = userAddress;
		this.userSalary = userSalary;
	}

	public User(int id, String userName, String userAddress, int userSalary) {
		super();
		this.id = id;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userSalary = userSalary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public int getUserSalary() {
		return userSalary;
	}

	public void setUserSalary(int userSalary) {
		this.userSalary = userSalary;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userAddress=" + userAddress + ", userSalary="
				+ userSalary + "]";
	}	
	
}
