package com.hillogy.controller;

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

	/**
	 * Converts a User entity to a UserDTO.
	 *
	 * @param user the User entity to convert.
	 * @return the converted UserDTO.
	 */
	public static UserDTO toDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		return dto;
	}
}