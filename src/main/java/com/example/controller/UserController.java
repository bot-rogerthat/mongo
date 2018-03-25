package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.example.util.TimestampPropertyEditor;
import com.example.validator.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserFormValidator userFormValidator;

	@InitBinder
	public void initBinderDate(WebDataBinder binder) {
		binder.registerCustomEditor(Timestamp.class, new TimestampPropertyEditor());
		binder.setValidator(userFormValidator);
	}

	@GetMapping("/")
	public String redirectIndex() {
		LOG.info("index()");
		return "redirect:/users";
	}

	@GetMapping("/users")
	public String showAllUsers(@RequestParam Map<String, String> params, ModelMap model) {
		LOG.info("showAllUsers()");
		model.put("users", userService.getSortUsers(params.get("sort")));
		return "users";
	}

	@GetMapping("/users/add")
	public String showAddUserForm(ModelMap model) {
		LOG.info("showAddUserForm()");
		model.put("user", new User());
		return "addUser";
	}

	@GetMapping("/users/{id}/update")
	public String showUpdateUserForm(@PathVariable(value = "id") Long id, ModelMap model) {
		User user = userService.getById(id);
		LOG.info("showUpdateUserForm() : {}", user);
		model.put("user", user);
		return "updateUser";
	}

	@PostMapping("/find")
	public String findUser(@RequestParam(value = "name") String name, ModelMap model) {
		List<User> users = userService.getByName(name);
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
			userService.addImage(user, file);
			userService.save(user);
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
			userService.addImage(user, file);
			userService.save(user);
			return "redirect:/users";
		}
	}

	@GetMapping("/users/{id}/delete")
	public String deleteUser(@PathVariable(value = "id") Long id) {
		LOG.info("deleteUser() : {}", id);
		userService.delete(id);
		return "redirect:/users";
	}
}
