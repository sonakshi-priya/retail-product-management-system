package com.cognizant.ecommerceportal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.ecommerceportal.dto.CartRequestDTO;



 class CartRequestDTOTest {
	
	CartRequestDTO cartRequestDTO=new CartRequestDTO();
	@Test
	void testGetProductId()
	{
		cartRequestDTO.setProductId(1);
		assertEquals(1,cartRequestDTO.getProductId());
	}
	@Test
	void testGetCustomerId()
	{
		cartRequestDTO.setCustomerId(1);
		assertEquals(1,cartRequestDTO.getCustomerId());
	}
	@Test
	void testGetQuantity()
	{
		cartRequestDTO.setQuantity(100);
		assertEquals(100,cartRequestDTO.getQuantity());
	}
	@Test
	void testGetZipCode()
	{
		cartRequestDTO.setZipcode("603301");
		assertEquals("603301",cartRequestDTO.getZipcode());
	}
	@Test
	void testAllArgsConstructor()
	{
		CartRequestDTO cR=new CartRequestDTO(1, 2,"603301", 100);
		assertEquals(1,cR.getProductId());
		assertEquals(2, cR.getCustomerId());
		assertEquals("603301",cR.getZipcode());
		assertEquals(100,cR.getQuantity());
	}

}
