package com.paytminsider.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.paytminsider.test.entites.Story;
import com.paytminsider.test.services.Services;



@SpringBootTest
public class PaytmIsiderTest {


	@Test
	public void assertEqualsTopStories() {
		Services service = new Services();
		List<Story> obj = service.topStories();
		assertEquals(obj.size(), 10);
	}
	

	@Test
	public void assertNotEqualsTopStories() {
		Services service = new Services();
		List<Story> obj = service.topStories();
		assertNotEquals(obj.size(), 5);
	}
	
	@Test
	public void testGetITems() {
		//Services service = new Services();
		List<Integer> obj = Services.getItems();
		assertEquals(obj.size(), 5);
		assertNotEquals("no values", obj.size(), 10);
	}
}
