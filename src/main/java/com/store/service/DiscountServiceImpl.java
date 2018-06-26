package com.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.store.dao.MongoDbOperation;
import com.store.model.UserInfo;
import com.store.utils.CommonUtil;

/**
 * This is a Service. It implements All the method listed in UserdataService
 * 
 * @author kaushal
 * @version 1.0
 * @since 26-06-2018
 */
@Service
public class DiscountServiceImpl implements DiscountService {
	
	private static String CUSTOMER_2_PLUS_YEAR ="customerForOver2Plus";

	@Autowired
	MongoDbOperation mongoDbOperation;

	@Override
	public Integer netpayableNonPercentDiscount(int billAmount) {
		// call utility method as it has flat discount
		return CommonUtil.netpayableForNonPercentDiscount(billAmount);
	}

	@Override
	public Integer netpayablePercentDiscount(String userId, int billAmount) {
		// call dao get the privilege and calculate discount

		// based on user id , get Privilege then get discount %
		String privilege = mongoDbOperation.getPrivilegeBasedOnUserId(userId);
		// get discount based on privilege
		int discountPercentage = mongoDbOperation.getDiscountByPrivilege(privilege);

		return CommonUtil.netpayableForPercentDiscount(billAmount, discountPercentage);

	}

	@Override
	public Integer netpayablePercentDiscountForCustomer(String userId, int billAmount) {

		// set Privilege for customer as per his eligibility
		UserInfo userInfo = mongoDbOperation.getUserInfo(userId);
		userInfo.setPrivilege(CUSTOMER_2_PLUS_YEAR);
		// get discount based on privilege
		int discountPercentage = mongoDbOperation.getDiscountByPrivilege(userInfo.getPrivilege());

		return CommonUtil.netpayableForPercentDiscount(billAmount, discountPercentage);

	}

	@Override
	public boolean CustomerPercentdiscountEligibility(String userId) {

		return mongoDbOperation.PercentdiscountEligibility(userId);
	}
}
