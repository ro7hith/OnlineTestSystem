package com.microservices.auth_service.Model.Repositories;

import com.microservices.auth_service.Model.Pojo.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<OurUsers, Integer> {
	Optional<OurUsers> findByEmail(String email);
}
