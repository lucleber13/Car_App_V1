package com.cbcoders.car_app_v1.Users.repository;

import com.cbcoders.car_app_v1.Users.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByRoleId(Integer roleId);
}
