package com.paytminsider.hackernews.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import com.paytminsider.hackernews.Cache;
import com.paytminsider.hackernews.entites.*;

/*
 *  Services class provides business logic to read and parse the data from hackernews api
*/

@Service
public class Services{
	Cache cacheObj =  new Cache();
	
	/*
	 *  List variables to keep data from hackernews api 
	*/
	
	List<Story> finalStoryObject = new ArrayList<>();
	List<CommentEntity> CommentOutput = new ArrayList<>();
	

	/*
	 * method to do a hit the hackernews api using jersey client to get the data
	 * 
	 * @param url  - string value containing the url
	 * @return response - Response object that contains the response from the rest call
	*/
	public static Response client(String url) {
		System.out.println(url);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(url);
		Invocation.Builder invocationBldr = target.request();
		Response response = invocationBldr.get();
		return response;
	}
	
	
	/*
	 * method to get all the items from hackernew api
	 * 
	 * @return stories - integer list that contains all the item numbers
	*/
	public static List<Integer> getItems() {
		
		Response response = Services.client(Variables.topStoriesURl.toString());
		List<Integer> stories = response.readEntity(List.class);
		System.out.println(stories.size());
		
		return stories;
	}
	
	/*
	 * method to get the top ten stories from the items 
	 * 
	 * @return finalStoryObject -  list of story objects that contains all the story information
	*/
	public List<Story> topStories() {
		
		finalStoryObject = (List<Story>) cacheObj.get("top-stories");
		if(finalStoryObject == null) {
			List<Integer> stories = Services.getItems();
			List<Story> storyObject = new ArrayList<>();
			stories.parallelStream().forEach(item -> {
				Response response = Services.client(Variables.storyURL.toString() + item+".json?print=pretty");
				Story obj = response.readEntity(Story.class);
				if(obj.getType().contentEquals("story")) {
					storyObject.add(obj);
				}
			});
			
			finalStoryObject = storyObject.stream().sorted(Comparator.comparingInt(Story::getScore).reversed()).limit(10).collect(Collectors.toList());
			cacheObj.put("top-stories", finalStoryObject);
		}
		
		return finalStoryObject;
}

	/*
	 * method to get the top ten comments for the given story
	 * 
	 * @param id - story item
	 * @return CommentOutput -  list of comment objects that contains all the comments for a given story
	*/
	public List<CommentEntity> topCommentsForStory(String id) {
		
		//check if the given id is of story 
		CommentOutput = (List<CommentEntity>) cacheObj.get("comments");
		System.out.println(CommentOutput);
		if(CommentOutput == null) {
			List<Comment> commObj = new ArrayList<Comment>();
			Response response = Services.client(Variables.storyURL.toString() + id+".json?print=pretty");
			Story obj = response.readEntity(Story.class);
			if(obj.getType().contentEquals("story")) {
				System.out.println(obj.toString());
				System.out.println(obj.getKids().size());
				obj.getKids().parallelStream().forEach(item -> {
					Response response1 = Services.client(Variables.storyURL.toString() + item+".json?print=pretty");
					Comment commentObj = response1.readEntity(Comment.class);
					commObj.add(commentObj);
				});
				List<Comment> finalCommentObj = commObj.parallelStream().sorted(Comparator.comparingInt(Comment::getKids).reversed()).limit(10).collect(Collectors.toList());
				CommentOutput = new Services().getAge(finalCommentObj);
				cacheObj.put("comments", finalCommentObj);
				}
		}
		return CommentOutput;
	}
	
	
	/*
	 * method to get the age of the user based on the profile creation date
	 * 
	 * @param finalCommentObj - comment object
	 * @return CommentOutput -  list of comment objects that contains all the comments along with user's age
	*/
	public List<CommentEntity> getAge(List<Comment> finalCommentObj) {
		Date today = new Date();
		List<CommentEntity> CommentOutput = new ArrayList<>();
		finalCommentObj.parallelStream().forEach(item -> {
			Response response = Services.client(Variables.userURL.toString()+ item.getBy()+".json?print=pretty");
			User output = response.readEntity(User.class);
			System.out.println(output.getCreated());
			Date date = new Date(Long.parseLong(output.getCreated()));
			
			CommentEntity obj = new CommentEntity();
			obj.setAge(Math.abs(today.getYear()-date.getYear()));
			obj.setBy(item.getBy());
			obj.setKids(item.getKids());
			obj.setText(item.getText());
			CommentOutput.add(obj);
		});
		
		return CommentOutput;
	}	
}