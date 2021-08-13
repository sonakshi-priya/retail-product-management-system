package com.cognizant.ecommerceportal.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWishlistRequestDTO {


	private Integer customerId;
	private Integer productId;
	private int quantity;	

}
