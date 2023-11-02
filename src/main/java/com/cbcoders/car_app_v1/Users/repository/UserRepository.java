package com.cbcoders.car_app_v1.Users.repository;

import com.cbcoders.car_app_v1.Users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	Optional<User> findByFirstName(String firstName);

	Optional<User> findByUserId(Long userId);
}
