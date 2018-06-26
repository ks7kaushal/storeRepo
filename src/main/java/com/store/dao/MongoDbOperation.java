package com.store.dao;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.store.model.Privilege;
import com.store.model.UserInfo;
import com.store.utils.CommonUtil;

/**
 * Implementation class of service interface. Its is a DAO layer provides the
 * methods for communicating to DB and acts as Repository.
 * 
 * @author kaushal
 * @version 1.0
 * @since 26.06.2018
 */
@Repository
public class MongoDbOperation {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbOperation.class);

	@Autowired
	MongoTemplate mongoTemplate;

	/**
	 * Method to Insert Data In User_Info collection.
	 * 
	 * @param userId
	 * @return
	 */
	public String getPrivilegeBasedOnUserId(String userId) {

		LOGGER.info("get privilege from User_Info collection with userId " + userId);
		try {
			return mongoTemplate.findById(userId, UserInfo.class).getPrivilege();
		} catch (Exception e) {
			LOGGER.info("Fail to find", e);
			return null;
		}

	}

	/**
	 * Method to Insert Data In User_Info collection.
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfo(String userId) {

		LOGGER.info("get userinfo from User_Info collection with userId " + userId);
		try {
			return mongoTemplate.findById(userId, UserInfo.class);
		} catch (Exception e) {
			LOGGER.info("Fail to find", e);
			return null;
		}

	}

	/**
	 * Method to Insert Data In Privilege collection.
	 * 
	 * @param privilege
	 * @return
	 */
	public int getDiscountByPrivilege(String privilege) {

		LOGGER.info("get discount from Privilege collection with privilege " + privilege);
		try {

			Query query = new Query();
			query.addCriteria(Criteria.where("privilege").is(privilege));

			Privilege privilObj = mongoTemplate.findOne(query, Privilege.class);

			return privilObj.getDiscount();

		} catch (Exception e) {
			LOGGER.info("Fail to find", e);
			return 1;
		}

	}

	/**
	 * Method to validate user from UserInfo collection for existing more than 2
	 * years.
	 * 
	 * @param userId
	 * @return
	 */

	public boolean PercentdiscountEligibility(String userId) {
		try {
			Date creationDate = mongoTemplate.findById(userId, UserInfo.class).getCreationDate();
			Date currentDate = new Date();

			long days = CommonUtil.betweenDates(creationDate, currentDate);

			if (days > 730) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			LOGGER.info("exception occured", e);
			return false;
		}

	}

}
