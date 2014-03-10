package com.cuitcc.controller;

import com.cuitcc.interceptor.BlogInterceptor;
import com.cuitcc.model.Blog;
import com.cuitcc.validator.BlogValidator;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	public void index() {
		setAttr("blogPage", Blog.dao.listblogs(getParaToInt(0, 1), 10));
		render("blog.html");
	}

	public void add() {
	}

	@Before(BlogValidator.class)
	public void save() {
		getModel(Blog.class).save();
		redirect("/blog");
	}

	public void edit() {
		setAttr("blog", Blog.dao.findById(getParaToInt()));
	}

	@Before(BlogValidator.class)
	public void update() {
		getModel(Blog.class).update();
		redirect("/blog");
	}

	public void delete() {
		Blog.dao.deleteById(getParaToInt());
		redirect("/blog");
	}
}
