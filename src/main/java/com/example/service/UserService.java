package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	@Value("${uploadedFolder}")
	private String uploadedFolder;

	@Autowired
	private UserDao userDao;

	public User getById(Long id) {
		return userDao.findOne(id);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public List<User> getByName(String name) {
		return userDao.getByName(name);
	}

	public List<User> getAll() {
		return userDao.findAll();
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
}
