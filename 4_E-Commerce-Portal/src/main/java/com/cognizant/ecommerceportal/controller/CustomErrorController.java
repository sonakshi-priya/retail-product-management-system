package com.cognizant.ecommerceportal.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

import com.cognizant.ecommerceportal.dto.ErrorResponseDTO;

@ControllerAdvice
@Controller
public class CustomErrorController implements ErrorController {

	public String getErrorPath() {
		return "error";
	}

	@ExceptionHandler({ HttpClientErrorException.class })
	public String globalException(HttpClientErrorException exception, HttpServletRequest request, Model m) {
		if (exception == null) {
			ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), exception.getRawStatusCode(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Unauthorized Access!!", request.getRequestURI());
			m.addAttribute("errorDto", dto);
			return "PageNotFound";
		}
		
		if (exception.getMessage().isBlank() || exception.getMessage().isEmpty()) {
			ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), exception.getRawStatusCode(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Unauthorized Access!!", request.getRequestURI());
			m.addAttribute("errorDto", dto);
			return "PageNotFound";
		}

		if (exception.getMessage().equalsIgnoreCase(
				"400 Cannot invoke \"com.cognizant.ecommerceportal.entity.JwtResponse.getJwttoken()\" because \"this.jwtResponse\" is null")) {
			ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), exception.getRawStatusCode(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Unauthorized Access!!", request.getRequestURI());
			m.addAttribute("errorDto", dto);

		} else {
			ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), exception.getRawStatusCode(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
			m.addAttribute("errorDto", dto);
		}

		return "PageNotFound";
	}

	@GetMapping("/error")
	public String getErrorPage(Model m, HttpServletRequest request) {

		ErrorResponseDTO dto = new ErrorResponseDTO(new Date(), 500, HttpStatus.NOT_FOUND.getReasonPhrase(),
				"Unknown Error Occured Please Trying LogIn Again", request.getRequestURI());
		m.addAttribute("errorDto", dto);
		return "PageNotFound";
	}

}
