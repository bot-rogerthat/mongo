package com.example.dao;

import com.example.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User, Long> {
	@Override
	User save(User s);

	@Override
	User findOne(Long id);

	@Override
	List<User> findAll();

	List<User> getByName(String name);
}
