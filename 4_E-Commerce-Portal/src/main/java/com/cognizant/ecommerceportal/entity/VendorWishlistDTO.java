package com.cognizant.ecommerceportal.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorWishlistDTO {

	private Integer customerId;
	private Integer productId;
	private int quantity;
	private Integer vendorId;
	private LocalDate addedToWishlistDate;

}
