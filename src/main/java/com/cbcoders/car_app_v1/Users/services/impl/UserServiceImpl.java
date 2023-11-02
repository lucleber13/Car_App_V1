package com.cbcoders.car_app_v1.Users.services.impl;

import com.cbcoders.car_app_v1.Exceptions.UserAlreadyExistsException;
import com.cbcoders.car_app_v1.Users.model.Role;
import com.cbcoders.car_app_v1.Users.model.User;
import com.cbcoders.car_app_v1.Users.repository.RoleRepository;
import com.cbcoders.car_app_v1.Users.repository.UserRepository;
import com.cbcoders.car_app_v1.Users.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User createUser(User createUser) {
		try {
			Optional<User> user = userRepository.findByEmail(createUser.getEmail());
			if (user.isPresent()) {
				throw new UserAlreadyExistsException("User already exists");
			}
			Optional<Role> role = roleRepository.findByRoleId(createUser.getRole().getRoleId());
			if (role.isPresent()) {
				createUser.setRole(role.get());
			}
			return userRepository.save(createUser);
		} catch (Exception e) {
			throw new UserAlreadyExistsException("User already exists");
		}
	}
}
