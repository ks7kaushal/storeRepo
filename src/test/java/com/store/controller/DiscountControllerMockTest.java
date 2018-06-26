package com.store.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.store.model.Privilege;
import com.store.model.UserInfo;
import com.store.service.DiscountService;

/**
 * This is DiscountControllerMockTest which does the unit test for DiscountController
 * request.
 * 
 * @author kaushal
 * @version 1.0
 * @since 26--6-2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class DiscountControllerMockTest {
	
	private MockMvc mockMvc;
	
	@Mock
	DiscountService discountService;
	
	@InjectMocks
	DiscountController discountController;
	
	UserInfo user;
    List<UserInfo> list;
	
	 @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(discountController)
	                .build();
	        
	        // Prepare UserInfo test Data
	        user = new UserInfo();
	        user.setFirstName("Fname");
	        user.setLastName("Lname");
	        user.setPrivilege("storeEmployee");
	        user.setCustomerOrAfiliatedCustomerId("1");
	        user.setCreationDate(new Date());
	        list = new ArrayList<UserInfo>();
	        list.add(user);
	        
	        // Prepare Privilege data
	        Privilege privilege = new Privilege();
	        privilege.setPrivilege("storeEmployee");
	        privilege.setDiscount(20);
	   
	    }
	 
	 @Test
	    public void testgetDiscountForNullUserId() throws Exception {

	        when(discountService.netpayableNonPercentDiscount(200)).thenReturn(190);
	        String uri = "/store/v1/discount?itemcategory=grocery&billAmount=200";
	        MvcResult result = mockMvc.perform(
	                MockMvcRequestBuilders.get(uri).accept(
	                        MediaType.APPLICATION_JSON)).andReturn();
	        String content = result.getResponse().getContentAsString();
	        int status = result.getResponse().getStatus();
	        verify(discountService, times(1)).netpayableNonPercentDiscount(200);
	        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
	        Assert.assertTrue(
	                "failure - expected HTTP response body to have a value",
	                content.trim().length() > 0);
	    }

}
