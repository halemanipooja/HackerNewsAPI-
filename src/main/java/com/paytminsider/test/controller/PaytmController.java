package com.paytminsider.test.controller;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import com.paytminsider.test.entites.CommentEntity;
import com.paytminsider.test.entites.Story;
import com.paytminsider.test.services.Services;


@Controller
@Path("/stories")
public class PaytmController{
	
	private final Services service = new Services();
	
	
	@GET
	@Path("/top-stories")
	@Produces("application/json")
	public Response topStories() {
		
		List<Story> finalStoryObject = service.topStories();
	    return Response.status(Response.Status.OK).entity(finalStoryObject).build();
	}
	
	@GET
	@Path("/comments/{id}")
	@Produces("application/json")
	public Response topCommentsForStory(@PathParam("id") String id) {
		
		//check if the given id is of story 
		List<CommentEntity> CommentOutput = service.topCommentsForStory(id);
		return Response.status(Response.Status.OK).entity(CommentOutput).build();
		
		
	}
	
}
