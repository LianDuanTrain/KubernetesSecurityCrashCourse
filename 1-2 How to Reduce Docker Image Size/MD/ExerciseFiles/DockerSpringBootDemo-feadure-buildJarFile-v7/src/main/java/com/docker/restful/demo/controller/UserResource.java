package com.docker.restful.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.restful.demo.entities.User;
import com.docker.restful.demo.services.UserService;

@RestController

// @Path("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	// @Produces("application/json")
	@CrossOrigin
	public List<User> getUsers() {
		final List<User> users = userService.findByPattern();
		return users;
	}

	@PostMapping(path = "/users")
	@CrossOrigin
	public void createUser(@RequestBody User user) {
		userService.save(user);
	
	}


	@RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)  
	@CrossOrigin
	public void deleteUser(@PathVariable("id") String userId) {
	//	System.out.println("Change.");
		userService.delete(userId);
	
	}
//	@GET
//	@Path("/{id}")
//	@Produces("application/json")
//	@CrossOrigin
//	public Response getUsers(@PathParam("id") final String userId) {
//		User user = userService.findById(userId);
//		if (user == null) {
//			return Response.status(404).build();
//		}
//		return Response.status(200).entity(user).build();
//	}

//	@PUT
//	@Path("/{id}")
//	@Consumes("application/json")
//	@Produces("application/json")
//	@CrossOrigin
//	public Response updateUser(@PathParam("id") final String userId, final User user) {
//		user.setId(userId);
//		userService.update(user);
//		return Response.status(200).entity(user).build();
//	}

}
