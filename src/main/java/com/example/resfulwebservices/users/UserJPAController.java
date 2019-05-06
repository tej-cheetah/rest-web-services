package com.example.resfulwebservices.users;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.resfulwebservices.exception.NoPostsExists;
import com.example.resfulwebservices.exception.UserNotFoundException;

@RestController
public class UserJPAController {

	@Autowired
	private UserDaoService service;
	
	@Autowired UserRepository userRepository;
	@Autowired PostRepository postRepository;
	
	@GetMapping(path = "/jpa/users")
	public List<User> users() {
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) throw new UserNotFoundException("No users in this environment exist");
		return users;
	}
	
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) throw new UserNotFoundException("id-" + id);
		EntityModel<User> model = new EntityModel<>(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(UserJPAController.class).users());
		model.add(linkTo.withRel("all-users"));
		return model;
	}
	
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsOfUser(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) throw new UserNotFoundException("id-" + id);
		List<Post> posts = userOptional.get().getPosts();
		if(posts.isEmpty()) throw new NoPostsExists("User has made no posts till date");
		return posts;
	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> addPost(@RequestBody Post post, @PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent()) throw new UserNotFoundException("id-" + id);
//		else {
			User user = userOptional.get();
			postRepository.save(post);
			
//		}
////		service.addPostsForUser(id, post).getPosts();
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/id")
//				.buildAndExpand(id).toUri();
//		return ResponseEntity.created(location).build();
		return null;
		
	}
	
//	@GetMapping(path = "/users/{id}/posts/{post_id}")
//	public UserPost getPostDetails(@PathVariable int id, @PathVariable int post_id) {
//		return service.getPostDetails(id, post_id);
//	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}