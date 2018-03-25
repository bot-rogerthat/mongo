package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Value("${uploadedFolder}")
	private String uploadedFolder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getById(String id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User saveOrUpdate(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public void addImage(User user, MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		if (ArrayUtils.isNotEmpty(bytes)) {
			Path path = Paths.get(uploadedFolder + file.getOriginalFilename());
			Files.write(path, bytes);
			user.setImage(file.getBytes());
		}
	}

	public List<User> getSortUsers(String field) {
		List<User> users = getAll();
		if ("email".equalsIgnoreCase(field)) {
			users = users.stream().sorted(Comparator.comparing(User::getEmail)).collect(Collectors.toList());
		} else if ("name".equalsIgnoreCase(field)) {
			users = users.stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
		}
		return users;
	}

	@Override
	public long getCount() {
		return userRepository.count();
	}
}
