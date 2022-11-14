package com.infosys.spa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.spa.models.User;
import com.infosys.spa.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//http://localhost:5000/user/all
	//Returns JSON Array of all users
	@GetMapping(value="/all")
	public List<User> findAllUsers(){
		return this.userService.findAllUsers();
	}
	
	//http://localhost:5000/user/username?username=Brandon
	/*Returns JSON value of: 
	{
	    "id": 5,
	    "userName": "Brandon",
	    "userAddress": "Colorado",
	    "userSalary": 1000
	} 
	*/
	@GetMapping(value = "/username")
	public User findUserByUserName(@RequestParam String username) {
		return this.userService.findByUserName(username);
	}
	
	//http://localhost:5000/user/new
	/*Must pass in JSON body without id 
	{
	    "userName": "Brandon",
	    "userAddress": "Colorado",
	    "userSalary": 1000
	} 
	*/
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void save(@RequestBody User user) {
		this.userService.save(user);
	}
	
	//http://localhost:5000/user/update
	/*Must pass in JSON body with id and all other info can be different
	{
		    "id":6,
			"userName":"Charles",
			"userAddress":"New York",
			"userSalary":100000
	} 
	*/
	@PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody User user) {
		this.userService.save(user);
	}
	
	//http://localhost:5000/user/delete/Brandon
	@DeleteMapping(value="/delete/{username}")
	public String deleteUserByUserName(@PathVariable String username) {
		User user = this.userService.findByUserName(username);
		if(user != null) {
			this.userService.delete(user);
			return "User " + user.getUserName() + " Successfully Deleted";
		} else {
			return "User Does Not Exist";
		}
	}
}
