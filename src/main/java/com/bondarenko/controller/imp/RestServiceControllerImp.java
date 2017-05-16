package com.bondarenko.controller.imp;

import java.util.Set;

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
import com.bondarenko.model.dto.RestNewUserDto;
import com.bondarenko.model.dto.RestProposalDto;
import com.bondarenko.model.dto.RestUserDto;
import com.bondarenko.service.ProposalService;
import com.bondarenko.service.RestService;
import com.bondarenko.service.UserInformationService;
import com.bondarenko.service.UserService;

@RestController
@RequestMapping (value = {"/rest","/rest/user","/rest/check","/rest/user/new"})
public class RestServiceControllerImp implements RestServiceController {
	@Autowired
	private ProposalService proposalService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserInformationService uiService;
	@Autowired
	private RestService restService;


	@GetMapping (value = "/clients")
	@Override
	public ResponseEntity<Set<RestUserDto>> getClients() throws RuntimeException {
		return new ResponseEntity<Set<RestUserDto>>(restService.getRestClientsDto(), HttpStatus.OK);
	}
	
	@Override
	@GetMapping (value = "/users")
	public ResponseEntity<Set<RestUserDto>> getUsers() throws RuntimeException {
		return new ResponseEntity<Set<RestUserDto>>(restService.getRestUsersDto(), HttpStatus.OK);
	}

	@Override
	@GetMapping (value = "/proposals")
	public ResponseEntity<Set<RestProposalDto>> getProposals() throws RuntimeException {
		return new ResponseEntity<Set<RestProposalDto>>(restService.getRestProposalsDto(), HttpStatus.OK);
	}

	@Override
	@PostMapping (value = "/client")
	public ResponseEntity<RestNewUserDto> makeNewClient(@RequestBody RestNewUserDto dto) throws RuntimeException {	
		return new ResponseEntity<RestNewUserDto>(restService.makeNewClient(dto), HttpStatus.OK);
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
