package com.paytminsider.hackernews.controller;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import com.paytminsider.hackernews.entites.CommentEntity;
import com.paytminsider.hackernews.entites.Story;
import com.paytminsider.hackernews.services.Services;

/*
 * Controller class that provides the rest end point
*/
@Controller
@Path("/stories")
public class PaytmController{
	
	private final Services service = new Services();
	
	
	/*
	 * method that provides Rest endpoint to get the top stories
	 * @return Response - response object with list of  Story class
	*/
	@GET
	@Path("/top-stories")
	@Produces("application/json")
	public Response topStories() {
		
		List<Story> finalStoryObject = service.topStories();
	    return Response.status(Response.Status.OK).entity(finalStoryObject).build();
	}
	
	
	
	/*
	 * method that provides Rest endpoint to get the top comments for a given story
	 * @param id - story number passed as path parameter 
	 * @return Response - response object with list of  CommentEntity class
	*/
	@GET
	@Path("/comments/{id}")
	@Produces("application/json")
	public Response topCommentsForStory(@PathParam("id") String id) {
		
		//check if the given id is of story 
		List<CommentEntity> CommentOutput = service.topCommentsForStory(id);
		return Response.status(Response.Status.OK).entity(CommentOutput).build();
		
		
	}
	
}
