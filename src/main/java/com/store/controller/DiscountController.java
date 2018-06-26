package com.store.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.store.service.DiscountService;

/**
 * This is DiscountController class consists method, used to handle HTTP request.
 * 
 * @author Kaushal
 * @version 1.0
 * @since 26-06-2018
 */
@RestController
@RequestMapping(value = "/store/v1")
public class DiscountController {

	@Autowired
	DiscountService discountService;

	/**
	 * test by echo message.
	 * 
	 * @return String
	 */
	@GetMapping("/echo/{str}")
	public String echoMsgCheck(@PathVariable String str) {
		return str;
	}
	
	 /**
     * get discount based on userId, CustomerOrAfiliatedCustomerId, billAmount & item category.
     * 
     * @param billAmount
     * @param userId
     * @param CustomerOrAfiliatedCustomerId
     * @param itemcategory
     * @return
     * @throws IOException
     */
	@GetMapping(value = "/discount", produces = MediaType.APPLICATION_JSON_VALUE)
	Integer getNetpayable(@RequestParam(value = "billAmount", required = true) Integer billAmount,
			@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "CustomerOrAfiliatedCustomerId", required = false) String CustomerOrAfiliatedCustomerId,
			@RequestParam(value = "itemcategory", required = true) String itemcategory) throws IOException {

		if (userId == null) {

			// 1st time visitor , not eligible for % based discount
			if (itemcategory.equalsIgnoreCase("grocery")) {

				// Non percent based discount
				return discountService.netpayableNonPercentDiscount(billAmount);
			} else {

				// No discount for grocery items
				return billAmount;
			}
		}
		else {

			if (itemcategory.equalsIgnoreCase("grocery")) {

				return discountService.netpayableNonPercentDiscount(billAmount);

			} else {
				// validate for existing user is a customer for over 2 years.
				if (CustomerOrAfiliatedCustomerId == null) {

					// he is a normal user so need to validate for his
					// eligibility of discount
					if (discountService.CustomerPercentdiscountEligibility(userId)) {
						return discountService.netpayablePercentDiscountForCustomer(userId, billAmount);
					} else
						return billAmount;
				} else
					return discountService.netpayablePercentDiscount(userId, billAmount);

			}
		}
	}

}