package com.michael.thirdpartyapi.repository;

import com.michael.thirdpartyapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
