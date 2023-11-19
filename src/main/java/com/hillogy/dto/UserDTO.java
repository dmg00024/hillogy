package com.hillogy.dto;

/**
 * Data Transfer Object for User data.
 */
public class UserDTO {
	private String username;

	/**
	 * Gets the username of the user.
	 *
	 * @return the username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the user.
	 *
	 * @param username the new username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}