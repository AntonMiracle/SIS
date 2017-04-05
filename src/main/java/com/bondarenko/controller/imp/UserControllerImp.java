package com.bondarenko.controller.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.bondarenko.controller.UserController;
import com.bondarenko.service.UserService;

@Controller
public class UserControllerImp implements UserController{
	@Autowired
	private UserService userService;
	

}
