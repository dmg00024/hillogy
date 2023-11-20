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
	
	/**
	 * Converts a User entity to a UserDTO object.
	 *
	 * @param user the User entity to be converted
	 * @return a UserDTO object with the username from the User entity
	 */
	public static UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        return dto;
    }
}