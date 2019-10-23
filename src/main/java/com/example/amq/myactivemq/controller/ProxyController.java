package com.example.amq.myactivemq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProxyController {


	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping
	public ResponseEntity<Object> placeOrder(@RequestBody final String method) {
		if (method.equals("create")) {
			System.out.println("Placing order");
			return new ResponseEntity("Unable to place order", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			/**
			 * This method is used for canceling the order if it the order is present in
			 * the excel.
			 * 
			 * @param orderId
			 * @return
			 */
			return new ResponseEntity("Order placed successfully.", HttpStatus.OK);
		}
	}
}