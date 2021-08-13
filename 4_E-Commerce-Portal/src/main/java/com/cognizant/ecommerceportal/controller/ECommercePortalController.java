package com.cognizant.ecommerceportal.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cognizant.ecommerceportal.dto.CartRequestDTO;
import com.cognizant.ecommerceportal.dto.CartResponseDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistDTO;
import com.cognizant.ecommerceportal.dto.CustomerWishlistRequestDTO;
import com.cognizant.ecommerceportal.dto.StatusDTO;
import com.cognizant.ecommerceportal.entity.JwtRequest;
import com.cognizant.ecommerceportal.entity.JwtResponse;
import com.cognizant.ecommerceportal.entity.Product;
import com.cognizant.ecommerceportal.service.ECommercePortalService;


@Controller
//@RequestMapping(value="{/ecommerce}")
public class ECommercePortalController {

	private JwtResponse jwtResponse;
	
	@Autowired
	private ECommercePortalService eCommercePortalServiceImpl;

	private static final Logger log = LoggerFactory.getLogger(ECommercePortalController.class);

	@GetMapping("/")
	public String initial(Model model) {
		log.info("Handling Request For Login");
		model.addAttribute("login", new JwtRequest());
		return "login";
	}

	@GetMapping("/login")
	public String login(Model model) {
		log.info("Handling Request For Login");
		model.addAttribute("login", new JwtRequest());
		return "login";
	}

	@PostMapping("/authenticate")
	public String login(@ModelAttribute("login") JwtRequest authenticationRequest, HttpServletResponse response,
			ModelMap map) {
		log.info("Handling Request For Authenticating User");
		try {
			this.jwtResponse = eCommercePortalServiceImpl.authticate(authenticationRequest, response);
		} catch (HttpClientErrorException e) {
			log.info("Wrong Login Credentials Redirecting For Login");
			map.addAttribute("error", "Wrong Credentials");
			return "login";
		}
		return "redirect:/index";
	}

	@GetMapping("/index")
	public String index(ModelMap map) {
		log.info("Handling Request For Index Page");
		try {
			List<Product> list = eCommercePortalServiceImpl.getAllProducts();
			map.addAttribute("list", list);
		} catch (HttpClientErrorException e) {
			log.info("Exception in Handling Index Page");
			throw e;
		}
		return "index";
	}

	@GetMapping("/searchByName")
	public String searchByName(@RequestParam("search") String name, ModelMap map) {
		log.info("Handling Request For /searchByName");
		List<Product> list = eCommercePortalServiceImpl.searchByName(name);
		map.addAttribute("list", list);
		return "index";
	}

	@PostMapping("/addToCart")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusDTO addToCart(@RequestBody CartRequestDTO request) {
		log.info("Handling Request For /addToCart");
		return eCommercePortalServiceImpl.addToCart(request);
	}

	@GetMapping("/getCart")
	public String getCart(ModelMap map, HttpServletResponse response) {
		log.info("Handling Request For /getCart");
		List<CartResponseDTO> cartList = null;
		try {
			cartList = eCommercePortalServiceImpl.getCart();
			if (cartList.isEmpty()) {
				map.addAttribute("errorHead", "Sorry!");
				map.addAttribute("errorMessage", "Your Cart is Empty!!");
			}

		} catch (Exception e) {
			throw e;
		}
		response.setHeader("Authorization", jwtResponse.getJwttoken());
		map.addAttribute("cartList", cartList);
		return "cart";
	}

	@PostMapping("/addToCustomerWishlist")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public StatusDTO addToCustomerWishList(@RequestBody CustomerWishlistRequestDTO customerWishlist) {
		log.info("Handling Request For /addToCustomerWishlist");
		StatusDTO status = eCommercePortalServiceImpl.addToCustomerWishList(customerWishlist);
		log.info(status.getMessage());
		return status;
	}

	@GetMapping("/getWishlist")
	public String viewAllWishlist(ModelMap map) {
		log.info("Handling Request For /getWishlist");
		List<CustomerWishlistDTO> list = eCommercePortalServiceImpl.getWishlist();
		if (list.isEmpty()) {
			map.addAttribute("errorHead", "Sorry!");
			map.addAttribute("errorMEssage", "Your Wishlist is Empty!!");
		}
		map.addAttribute("list", list);
		return "wishlist";
	}

	@PostMapping("/addProductRating/{productId}/{rating}")
	public String setRating(@PathVariable int productId, @PathVariable int rating) {
		log.info("Handling Request For /addProductRating");
		eCommercePortalServiceImpl.setRating(productId, rating);
		return "goToIndex";
	}

	@GetMapping("/logout")
	public String logout(ModelMap map) {
		log.info("Handling Request For Logout");
		jwtResponse = null;
		eCommercePortalServiceImpl.logout();
		map.addAttribute("login", new JwtRequest());
		map.addAttribute("error", "You have logged out please login again if you want to access the app");
		return "login";
	}
}
