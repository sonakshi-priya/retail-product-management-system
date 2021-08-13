package com.cognizant.ecommerceportal.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product 
{
	private Integer id;
	private String name;
	private double price;
	private String description;
	private String imageName;
	private float rating;
	private int noOfUsersRated;
	
}
