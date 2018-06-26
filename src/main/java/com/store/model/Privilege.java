package com.store.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is POJO class acts as Model layer, which maps to the "Privilege_Info"
 * collection in DB
 * 
 * @author kaushal
 * @version 1.0
 * @since 26.06.2018
 */

@Document(collection = "Privilege")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Privilege {

	private String privilege;
	private int discount;

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Privilege [privilege=" + privilege + ", discount=" + discount + "]";
	}

}
