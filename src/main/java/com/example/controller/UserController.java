package com.example.controller;

import com.example.model.User;
import com.example.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/")
	public String redirectIndex() {
		LOG.info("index()");
		return "redirect:/users";
	}

	@GetMapping("/users")
	public String showAllUsers(@RequestParam Map<String, String> params, ModelMap model) {
		LOG.info("showAllUsers()");
		model.put("users", userServiceImpl.getSortUsers(params.get("sort")));
		return "users";
	}

	@GetMapping("/users/add")
	public String showAddUserForm(ModelMap model) {
		LOG.info("showAddUserForm()");
		model.put("user", new User());
		return "addUser";
	}

	@GetMapping("/users/{id}/update")
	public String showUpdateUserForm(@PathVariable(value = "id") String id, ModelMap model) {
		User user = userServiceImpl.getById(id);
		LOG.info("showUpdateUserForm() : {}", user);
		model.put("user", user);
		return "updateUser";
	}

	@PostMapping("/find")
	public String findUser(@RequestParam(value = "name") String name, ModelMap model) {
		List<User> users = userServiceImpl.getByName(name);
		LOG.info("findUser() : {}", users);
		model.put("users", users);
		return "users";
	}

	@PostMapping("/users/{id}/update")
	public String updateUser(@RequestParam("file") MultipartFile file,
							 @Validated @ModelAttribute("user") User user,
							 BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return "updateUser";
		} else {
			LOG.info("updateUser() : {}", user);
			userServiceImpl.addImage(user, file);
			userServiceImpl.saveOrUpdate(user);
			return "redirect:/users";
		}
	}

	@PostMapping("/users/add")
	public String addUser(@RequestParam("file") MultipartFile file,
						  @Validated @ModelAttribute("user") User user,
						  BindingResult result) throws IOException {
		if (result.hasErrors()) {
			return "addUser";
		} else {
			LOG.info("addUser() : {}", user);
			userServiceImpl.addImage(user, file);
			userServiceImpl.saveOrUpdate(user);
			return "redirect:/users";
		}
	}

	@GetMapping("/users/{id}/delete")
	public String deleteUser(@PathVariable(value = "id") String id) {
		LOG.info("deleteUser() : {}", id);
		userServiceImpl.delete(id);
		return "redirect:/users";
	}

	@GetMapping("/count")
	public String showCount(ModelMap model) {
		LOG.info("showCount()");
		model.put("count", userServiceImpl.getCount());
		return "count";
	}
}
