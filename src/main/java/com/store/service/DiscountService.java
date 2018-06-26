package com.store.service;

/**
 * This is an interface, Define all the methods implemented in
 * DiscountServiceImpl class.
 * 
 * @author kaushal
 * @version 1.0
 * @since 26-06-2018
 */
public interface DiscountService {

	/**
	 * Method to fetch Data from User_Info collection.
	 * 
	 * @param billAmount
	 * @return
	 */
	Integer netpayableNonPercentDiscount(int billAmount);

	/**
	 * Method to fetch Data from User_Info collection.
	 * 
	 * @param billAmount
	 * @param userId
	 * @return
	 */
	Integer netpayablePercentDiscount(String userId, int billAmount);

	/**
	 * Method to fetch Data from User_Info collection.
	 * 
	 * @param billAmount
	 * @param userId
	 * @return
	 */
	Integer netpayablePercentDiscountForCustomer(String userId, int billAmount);

	/**
	 * Method to Insert Data In User_Info collection.
	 * 
	 * @param userId
	 * @return
	 */
	boolean CustomerPercentdiscountEligibility(String userId);

}
