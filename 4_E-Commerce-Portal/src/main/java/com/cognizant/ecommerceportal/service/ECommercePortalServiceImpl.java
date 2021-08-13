package com.cognizant.ecommerceportal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cognizant.ecommerceportal.dto.CartDTO;
import com.cognizant.ecommerceportal.dto.CartRequestDTO;
import com.cognizant.ecommerceportal.dto.CartResponseDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistRequestDTO;
import com.cognizant.ecommerceportal.dto.StatusDTO;
import com.cognizant.ecommerceportal.entity.JwtRequest;
import com.cognizant.ecommerceportal.entity.JwtResponse;
import com.cognizant.ecommerceportal.entity.Product;


@Service
public class ECommercePortalServiceImpl implements ECommercePortalService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api-server.uri}")
	private String uri;
	
	
	HttpHeaders headers = new HttpHeaders();

	HttpEntity<String> entity = new HttpEntity<>(headers);

	private JwtResponse jwtResponse;

	private static final Logger log = LoggerFactory.getLogger(ECommercePortalServiceImpl.class);

	@Override
	public JwtResponse authticate(JwtRequest authenticationRequest, HttpServletResponse response)
			throws HttpClientErrorException {
		log.info("Sending Request to Authorization Microservice");
		ResponseEntity<JwtResponse> responseEntity = restTemplate.postForEntity(uri + "/authenticate",
				authenticationRequest, JwtResponse.class);
		this.jwtResponse = responseEntity.getBody();
		this.jwtResponse.setJwttoken("Bearer " + jwtResponse.getJwttoken());
		response.setHeader("Authorization", jwtResponse.getJwttoken());
		response.addHeader("customerId", String.valueOf(jwtResponse.getCustomerId()));
		log.info("Sending Ro Authorization Microservice");
		return this.jwtResponse;
	}

	@Override
	public List<Product> getAllProducts() throws HttpClientErrorException {
		log.info("Sending Request to Product Microservice /getAll From E-commerce-Portel");
		List<Product> list = new ArrayList<>();
		try {
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", jwtResponse.getJwttoken());
			entity = new HttpEntity<>(headers);
			ResponseEntity<List<Product>> reponseEntity = restTemplate.exchange(
					uri+"/product/getAllProducts", HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Product>>() {
					});
			list = reponseEntity.getBody();
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return list;
	}

	@Override
	public List<Product> searchByName(String name) {
		log.info("Sending Request to Product Microservice /searchByName From E-commerce-Portel");
		String nameTemp = name.toLowerCase();
		Product product;
		try {
			int i = Integer.parseInt(name);
			ResponseEntity<Product> responseEntity = restTemplate.exchange(
					uri+"/product/searchProductById/" + nameTemp, HttpMethod.GET, entity,
					new ParameterizedTypeReference<Product>() {
					});
			product = responseEntity.getBody();
		} catch (NumberFormatException nfe) {
			nameTemp = nameTemp.substring(0, 1).toUpperCase() + nameTemp.substring(1);
			ResponseEntity<Product> responseEntity = restTemplate.exchange(
					uri+"/product/searchProductByName/" + nameTemp, HttpMethod.GET, entity,
					new ParameterizedTypeReference<Product>() {
					});
			product = responseEntity.getBody();
		}
		List<Product> list = new ArrayList<>();
		list.add(product);
		return list;
	}

	@Override
	public StatusDTO addToCart(CartRequestDTO request) {

		log.info("Sending Request to Cart Microservice /addToCart From E-commerce-Portel");
		StatusDTO status = new StatusDTO();
		String s = "";
		try {
			request.setCustomerId(jwtResponse.getCustomerId());
			HttpEntity<CartRequestDTO> entityTemp = new HttpEntity<>(request, headers);
			status = restTemplate.postForObject(uri + "/cart/addProductToCart", entityTemp, StatusDTO.class);
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		if (status.getMessage().length() > 50) {
			s = status.getMessage();
			s = s.substring(7, s.length() - 1);
			status.setMessage(s);

		} else {
			s = "{\"message\":\"" + status.getMessage() + "\"}";
			status.setMessage(s);
		}

		return status;
	}

	@Override
	public List<CartResponseDTO> getCart() throws HttpClientErrorException {
		log.info("Sending Request to Cart Microservice /getCart From E-commerce-Portel");
		List<CartResponseDTO> list = new ArrayList<>();
		try {
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", jwtResponse.getJwttoken());
			entity = new HttpEntity<>(headers);
			ResponseEntity<List<CartResponseDTO>> responseEntity = restTemplate.exchange(
					uri+"/cart/getCart/" + jwtResponse.getCustomerId(), HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<CartResponseDTO>>() {
					});
			list = responseEntity.getBody();
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return list;
	}

	@Override
	public List<CustomerWishlistDTO> getWishlist() {
		log.info("Sending Request to Cart Microservice /getWishList From E-commerce-Portel");
		List<CustomerWishlistDTO> list = new ArrayList<>();
		try {
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.set("Authorization", jwtResponse.getJwttoken());
			entity = new HttpEntity<>(headers);
			ResponseEntity<List<CustomerWishlistDTO>> responseEntity = restTemplate.exchange(
					uri+"/cart/getWishlist/" + jwtResponse.getCustomerId(), HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<CustomerWishlistDTO>>() {
					});
			list = responseEntity.getBody();
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return list;
	}

	@Override
	public StatusDTO addToCustomerWishList(CustomerWishlistRequestDTO customerWishlist) {
		log.info("Sending Request to Cart Microservice /addToCustomerWishList");
		customerWishlist.setCustomerId(this.jwtResponse.getCustomerId());
		log.info("Adding to wishlist");
		HttpEntity<CustomerWishlistRequestDTO> entityTemp = new HttpEntity<>(customerWishlist, headers);
		return restTemplate.postForObject(uri + "/cart/addToCustomerWishlist", entityTemp, StatusDTO.class);
	}

	@Override
	public Product setRating(int productId, int rating) {
		log.info("Sending Request to Product Microservice /addRating From E-commerce-Portel");
		return restTemplate.postForObject(uri+"/product/addProductRating/" + productId + "/" + rating,
				entity, Product.class);
	}

	@Override
	public void logout() {
		this.jwtResponse = null;

	}

}
