package com.hillogy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;   
    
    /**
     * 
     */
    public User() {}
    
    /**
     * 
     * @param username
     * @param password
     */
    public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
     * 
     * @return
     */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}