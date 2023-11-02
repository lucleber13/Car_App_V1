package com.cbcoders.car_app_v1.Users.controller;

import com.cbcoders.car_app_v1.Users.model.User;
import com.cbcoders.car_app_v1.Users.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		user = userService.createUser(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userId}")
				.buildAndExpand(user.getUserId())
				.toUri();
		return ResponseEntity.created(location).body(user);
	}
}
