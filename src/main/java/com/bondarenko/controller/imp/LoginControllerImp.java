package com.bondarenko.controller.imp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bondarenko.controller.LoginController;

@Controller
public class LoginControllerImp implements LoginController {

	@Override
	@RequestMapping (value = {"/","login"}, method = RequestMethod.GET)
	public String login() throws RuntimeException {
		return "login";
	}

	@Override
	@RequestMapping (value = {"home"}, method = RequestMethod.GET)
	public String home() throws RuntimeException {
		return "home";
	}
}
