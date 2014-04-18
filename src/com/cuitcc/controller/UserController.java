package com.cuitcc.controller;

import com.cuitcc.model.Post;
import com.cuitcc.model.Review;
import com.cuitcc.model.User;
import com.jfinal.core.Controller;

public class UserController extends Controller {
	public void index() {

		long userId = 1;
		setAttr("user", User.dao.getUserById(userId));
		setAttr("postPage",
				Post.dao.listPostsForUser(userId, getParaToInt(1, 1), 15));

		setAttr("reviewPage",
				Review.dao.listReviewForUser(userId, getParaToInt(1, 1), 15));
		render("/common/user.html");
	}
}