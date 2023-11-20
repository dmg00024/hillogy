package com.hillogy.converter;

import com.hillogy.dto.UserDTO;
import com.hillogy.model.User;

public class UserConverter {
	/**
	 * Converts a UserDTO to a User entity.
	 *
	 * @param dto the UserDTO to convert.
	 * @return the converted User entity.
	 */
	public static User toEntity(UserDTO dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		return user;
	}
	
	
}