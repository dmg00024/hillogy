package com.hillogy.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.hillogy.dto.UserDTO;
import com.hillogy.model.User;

class UserConverterTest {

	@Test
	void testToEntity() {
		UserDTO dto = new UserDTO();
		dto.setUsername("testUser");

		User user = UserConverter.toEntity(dto);

		assertEquals(dto.getUsername(), user.getUsername());

	}

	@Test
	void testToDto() {
		User user = new User();
		user.setUsername("testUser");

		UserDTO dto = UserConverter.toDto(user);

		assertEquals(user.getUsername(), dto.getUsername());
	}

}
