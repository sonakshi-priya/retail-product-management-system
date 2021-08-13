package com.cognizant.ecommerceportal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

	private Integer vendorId;
	private String vendorName;
	private double deliveryCharge;
    private float rating;

}
