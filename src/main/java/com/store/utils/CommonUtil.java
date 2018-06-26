package com.store.utils;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines helper/util methods that performs common functionalities which
 * doesn't depend on the state of the object.
 *
 * @author kaushal
 * @version 1.0
 * @since 26.06.2018
 */

public class CommonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

	/* Private Constructor for CommonUtil classe */
	private CommonUtil() {
	}

	/* Returning a object of CommonsUtil class */
	public static CommonUtil getInstance() {
		return new CommonUtil();
	}

	/**
	 * Util method to calculate net payable billAmount for non percent discount.
	 *
	 * @param billAmount
	 * @return
	 */

	public static Integer netpayableForNonPercentDiscount(int billAmount) {

		if (billAmount < 100) {

			return billAmount;
		} else {

			return billAmount - billAmount / 100 * 5;
		}

	}

	/**
	 * Util method to calculate net payable billAmount for percent discount.
	 *
	 * @param billAmount
	 * @param discountPercentage
	 * @return
	 */

	public static Integer netpayableForPercentDiscount(int billAmount, int discountPercentage) {

		int netpay = 0;
		try {

			netpay = billAmount - billAmount * discountPercentage / 100;

		} catch (NullPointerException ex) {

			ex.printStackTrace();

		}
		return netpay;

	}

	/**
	 * Util method to calculate no of days between two days.
	 *
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static long betweenDates(Date firstDate, Date secondDate) throws IOException
	{
	    return ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
	}

}
