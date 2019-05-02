package com.example.resfulwebservices.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.resfulwebservices.exception.UserNotFoundException;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 4;
	
	static {
//		users.add(new User(1, "Tejas", new Date(), new ArrayList<UserPost>()));
//		users.add(new User(2, "Sanil", new Date(), new ArrayList<UserPost>()));
//		users.add(new User(3, "Nidhi", new Date(), new ArrayList<UserPost>()));
//		users.add(new User(4, "Rajat", new Date(), new ArrayList<UserPost>()));
		users.add(new User(1, "Tejas", new Date()));
		users.add(new User(2, "Sanil", new Date()));
		users.add(new User(3, "Nidhi", new Date()));
		users.add(new User(4, "Rajat", new Date()));

	}
	
	public List<User> getUsers() {
		return users;
	}
	
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId() == id) {
				return user;
			}
		}
		throw new UserNotFoundException("id-" + id);
	}

	public User addUser(User user) {
		if(user.getId() == 0) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
//	public User addPostsForUser(int id, UserPost post) {
//		User user = findOne(id);
//		if(user != null) {
//			List<UserPost> posts = user.getPosts();
//			int size = posts.size();
//			post.setId(++size);
//			System.out.println(post.toString());
//			posts.add(post);
//			user.setPosts(posts);
//			return user;
//		}
//		else throw new UserNotFoundException("id- " + id);
//	}
//	
//	public List<UserPost> getUserPosts(int id) {
//		User user = findOne(id);
//		List<UserPost> posts = user.getPosts();
//		if(posts.isEmpty()) throw new NoPostsExists("user has never made any post");
//		else return posts;
//	}
//	
//	public UserPost getPostDetails(int id, int post_id) {
//		List<UserPost> posts = findOne(id).getPosts();
//		UserPost userPost = searchPostViaId(post_id, posts);
//		if(userPost!= null) return userPost;
//		else throw new NoPostsExists("No post with that post_id exists for the current user");
//	}
//	
//	private UserPost searchPostViaId(int id, List<UserPost> posts) {
//		for(UserPost post: posts) {
//			if(post.getId() == id) return post;
//		}
//		return null;
//	}
	
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
