package com.example.resfulwebservices.users;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.resfulwebservices.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;
	
	@GetMapping(path = "/users")
	public List<User> users() {
		List<User> users = service.getUsers();
		if(users.isEmpty()) throw new UserNotFoundException("No users in this environment exist");
		return users;
	}
	
	@GetMapping(path = "users/{id}")
	public User getUser(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) throw new UserNotFoundException("id-" + id);
		return user;
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = service.addUser(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/users/{id}/posts")
	public List<UserPost> retrieveAllPostsOfUser(@PathVariable int id) {
		return service.getUserPosts(id);
	}
	
	@PostMapping(path = "/users/{id}/posts")
	public ResponseEntity<Object> addPost(@RequestBody UserPost post, @PathVariable int id) {
		service.addPostsForUser(id, post).getPosts();
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/id")
				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/users/{id}/posts/{post_id}")
	public UserPost getPostDetails(@PathVariable int id, @PathVariable int post_id) {
		return service.getPostDetails(id, post_id);
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if(user == null) throw new UserNotFoundException("id- " + id);
	}
}