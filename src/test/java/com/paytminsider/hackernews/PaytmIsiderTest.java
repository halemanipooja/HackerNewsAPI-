package com.paytminsider.hackernews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.paytminsider.hackernews.entites.CommentEntity;
import com.paytminsider.hackernews.entites.Story;
import com.paytminsider.hackernews.services.Services;

/*
 * Test class to test the methods written in Service class
*/

@SpringBootTest
public class PaytmIsiderTest {


	/*
	 * AsserEquals Methos
	 * Method to test whether the output from hacker news api in service class returns only 10 story items 
	*/
	@Test
	public void assertEqualsTopStories() {
		Services service = new Services();
		List<Story> obj = service.topStories();
		assertEquals(obj.size(), 10);
	}
	

	/*
	 * AsserNotEquals Method
	 * Method to test whether the output from hacker news api in service class doesn't match with 10 
	*/
	@Test
	public void assertNotEqualsTopStories() {
		Services service = new Services();
		List<Story> obj = service.topStories();
		assertNotEquals(obj.size(), 5);
	}
	
	
	/*
	 * AsserEquals Method
	 * Method to test whether the total number of items from hacker news api is equal to 10 
	*/
	@Test
	public void testGetITems() {
		//Services service = new Services();
		List<Integer> obj = Services.getItems();
		assertEquals(obj.size(), 5);
	}
	
	/*
	 * AsserEquals Methos
	 * Method to test whether the output from hacker news api in service class returns only 10 comments for a given story 
	*/
	@Test
	public void assertEqualsTopComments() {
		String storynumber  = "23577265";
		Services service = new Services();
		List<CommentEntity> obj = service.topCommentsForStory(storynumber);
		assertEquals(obj.size(), 10);
	}
	
}
