package com.bondarenko.controller.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bondarenko.controller.RestServiceController;
import com.bondarenko.model.User;
import com.bondarenko.model.UserInformation;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;
import com.bondarneko.dto.NewUserDto;

@RestController
@RequestMapping (value = {"/rest","/rest/user","/rest/check"})
public class RestServiceControllerImp implements RestServiceController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserInformationService uiService;

	@Override
	@GetMapping (value = "/users")
	public ResponseEntity<List<User>> getUsers() throws RuntimeException {
		List<User> users = new ArrayList<>();
		for (User user : userService.getUsers()) {
			users.add(user);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}
	
	

	@Override
	@PostMapping (value = "/new")
	public ResponseEntity<User> saveUser(@RequestBody NewUserDto dto) throws RuntimeException {
		User user = new User();
		if (dto != null) {
			UserInformation ui = new UserInformation();
			user.setUserInformation(ui);
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			ui.setPhone(dto.getPhone());
			ui.setMail(dto.getMail());
			ui.setName(dto.getName());
			ui.setSurname(dto.getSurname());
			user = userService.save(user);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	@GetMapping ("/usernamefree/{username}")
	public ResponseEntity<Boolean> isUsernameUnique(@PathVariable ("username") String username)
			throws RuntimeException {
		return new ResponseEntity<Boolean>(userService.isUsernameUnique(username), HttpStatus.OK);
	}

	@Override
	@GetMapping ("/phonefree/{phone}")
	public ResponseEntity<Boolean> isPhoneUnique(@PathVariable ("phone") String phone) throws RuntimeException {
		return new ResponseEntity<Boolean>(uiService.isPhoneUnique(phone), HttpStatus.OK);
	}

	@Override
	@GetMapping ("/{id}")
	public ResponseEntity<User> getById(@PathVariable ("id") Long id) throws RuntimeException {
		return new ResponseEntity<User>(userService.getById(id), HttpStatus.OK);
	}

	@Override
	@GetMapping ("/authentication/{username}/{password}")
	public ResponseEntity<Boolean> checkUsernameAndPassword(@PathVariable ("username") String username,
			@PathVariable ("password") String password) throws RuntimeException {
		return new ResponseEntity<Boolean>(userService.isAuthenticationCorrect(username, password), HttpStatus.OK);
	}

}
