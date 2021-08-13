package com.cognizant.ecommerceportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.ecommerceportal.entity.JwtRequest;

class JwtRequestTest {
	JwtRequest jwtRequest = new JwtRequest();

	@Test
	void getUserName() {
		jwtRequest.setUsername("Prakash");
		assertEquals("Prakash", jwtRequest.getUsername());
	}

	@Test
	void testGetPassword() {
		jwtRequest.setPassword("Prakash");
		assertEquals("Prakash", jwtRequest.getPassword());
	}

	@Test
	void testParaterizedConstructor() {
		JwtRequest jR = new JwtRequest("Prakash", "Prakash");
		assertEquals("Prakash", jR.getUsername());
		assertEquals("Prakash", jR.getPassword());
	}

}
