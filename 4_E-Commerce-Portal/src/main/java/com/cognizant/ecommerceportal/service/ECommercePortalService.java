package com.cognizant.ecommerceportal.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.HttpClientErrorException;

import com.cognizant.ecommerceportal.dto.CartRequestDTO;
import com.cognizant.ecommerceportal.dto.CartResponseDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistRequestDTO;
import com.cognizant.ecommerceportal.dto.StatusDTO;
import com.cognizant.ecommerceportal.entity.JwtRequest;
import com.cognizant.ecommerceportal.entity.JwtResponse;
import com.cognizant.ecommerceportal.entity.Product;

public interface ECommercePortalService {

	JwtResponse authticate(JwtRequest authenticationRequest, HttpServletResponse response)
			throws HttpClientErrorException;

	List<Product> getAllProducts() throws HttpClientErrorException;

	List<Product> searchByName(String name);

	StatusDTO addToCart(CartRequestDTO request);

	List<CartResponseDTO> getCart() throws HttpClientErrorException;

	List<CustomerWishlistDTO> getWishlist();

	StatusDTO addToCustomerWishList(CustomerWishlistRequestDTO customerWishlist);

	Product setRating(int productId, int rating);

	void logout();

}