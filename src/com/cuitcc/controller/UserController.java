package com.cuitcc.controller;

import com.cuitcc.interceptor.UserInterceptor;
import com.cuitcc.model.User;
import com.cuitcc.validator.UserValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 */
@Before(UserInterceptor.class)
public class UserController extends Controller {
	public void index() {
		setAttr("userPage", User.dao.listUsers(getParaToInt(0, 1), 10));
		render("user.html");
	}

	public void add() {
	}

	public void save() {
		getModel(User.class).save();
		redirect("/user");
	}

	public void edit() {
		setAttr("user", User.dao.findById(getParaToInt()));
	}

	@Before(UserValidator.class)
	public void update() {
		getModel(User.class).update();
		redirect("/user");
	}

	public void delete() {
		User.dao.deleteById(getParaToInt());
		redirect("/user");
	}
}
