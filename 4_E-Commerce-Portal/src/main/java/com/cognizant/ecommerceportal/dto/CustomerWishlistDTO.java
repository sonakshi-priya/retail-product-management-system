package com.cognizant.ecommerceportal.dto;

import java.time.LocalDate;

import com.cognizant.ecommerceportal.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishlistDTO {
	
	private Integer wishListId;
	private int quantity;	
	private LocalDate addedToWishlistDate;
	private Integer customerId;
	private Product product;
		
	
		
	
}
