package com.store.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This is POJO class acts as Model layer, which maps to the "User_Info"
 * collection in DB
 * 
 * @author kaushal
 * @version 1.0
 * @since 26.06.2018
 */

@Document(collection = "User_Info")
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private String privilege;
	private String CustomerOrAfiliatedCustomerId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date creationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	public Date getCreationDate() {

		return this.creationDate == null ? null : (Date) this.creationDate.clone();
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = (Date) creationDate.clone();
	}

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getCustomerOrAfiliatedCustomerId() {
		return CustomerOrAfiliatedCustomerId;
	}

	public void setCustomerOrAfiliatedCustomerId(String customerOrAfiliatedCustomerId) {
		CustomerOrAfiliatedCustomerId = customerOrAfiliatedCustomerId;
	}

	@Override
	public String toString() {
		return "UserInfo [firstName=" + firstName + ", lastName=" + lastName + ", creationDate=" + creationDate
				+ ", privilege=" + privilege + ", CustomerOrAfiliatedCustomerId=" + CustomerOrAfiliatedCustomerId +"]";
	}

}
