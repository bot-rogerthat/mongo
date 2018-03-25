package com.example.repository;

import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
	List<User> findByName(String name);
}
