package com.cognizant.cartmicroservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.cartmicroservice.dto.CartDTO;
import com.cognizant.cartmicroservice.dto.CartRequestDTO;
import com.cognizant.cartmicroservice.dto.CartResponseDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistDTO;
import com.cognizant.cartmicroservice.dto.CustomerWishlistRequestDTO;
import com.cognizant.cartmicroservice.dto.StatusDTO;
import com.cognizant.cartmicroservice.exception.QuantityLimitExceededException;
import com.cognizant.cartmicroservice.service.CartService;
import com.cognizant.cartmicroservice.service.CustomerWishlistService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value={"/cart"})
public class CartController {
	
	@Autowired
	private CartService cartServiceImpl;
	
	@Autowired
	private CustomerWishlistService customerWishlistServiceImpl;
	
	@PostMapping("/addProductToCart")
	public StatusDTO addProductToCart(@RequestBody CartRequestDTO cartRequestDTO){
		log.info("Add Product to Cart from addProductToCart Controller method");
		StatusDTO statusDTO = new StatusDTO(cartServiceImpl.addProductToCart(cartRequestDTO));
		log.info("Add product to cart service executed successfuly");
		return statusDTO;
		
	}
	
	@GetMapping("/getCart/{customerId}")
	public List<CartResponseDTO> getCartList(@PathVariable Integer customerId) {
		log.info("Get CartList from  getCartList Controller method");
		List<CartResponseDTO> cartList = cartServiceImpl.getCartList(customerId);
		log.info("get cartList by customer id service executed successfuly");
		return cartList;
	}
	
	@PostMapping("/addToCustomerWishlist")
	public StatusDTO addToCustomerWishList(@RequestBody CustomerWishlistRequestDTO customerWishlist) {
		log.info("Add customer wishList from addToCustomerWishList controller method");
		return customerWishlistServiceImpl.addToCustomerWishList(customerWishlist);
	}
	
	@GetMapping("/getWishlist/{customerId}")
	public List<CustomerWishlistDTO> getAllWishlist(@PathVariable Integer customerId){
		log.info("Get cart wishlist from getAllWishlist Controller method");
		List<CustomerWishlistDTO> customerDTOList= customerWishlistServiceImpl.getAllWishlist(customerId);		
		log.info("get cart service call ended");
        return customerDTOList;
	}

}
