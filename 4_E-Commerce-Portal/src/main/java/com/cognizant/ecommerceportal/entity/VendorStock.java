package com.cognizant.ecommerceportal.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorStock 
{
	private Integer id;
	private Integer productId;
	private Integer vendorId;
	private int stockInHand;
	private LocalDate expectedStockReplinshmentDate;
	
}
