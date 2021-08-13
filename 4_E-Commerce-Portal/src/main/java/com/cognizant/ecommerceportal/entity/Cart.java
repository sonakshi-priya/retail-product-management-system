package com.cognizant.ecommerceportal.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private Integer cartId;
	private Integer productId;
	private String zipcode;
	private LocalDate deliveryDate;
	private Integer vendoreId;
	private Integer customerId;
	private int quantity;
}
