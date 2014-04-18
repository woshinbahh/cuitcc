package com.cuitcc.controller;

import java.net.URLDecoder;
import java.util.List;

import com.cuitcc.model.Global_msg;
import com.cuitcc.model.Post;
import com.cuitcc.model.Review;
import com.cuitcc.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
/**
 * @comment 后台用户管理控制器
 * @author luyi
 *
 */
public class UserManController extends Controller {
	
	public void index() {
		
		new Global_msg().set("content","呵呵").save();
		render("static/index.html");
	}
	/*
	 * @comment 添加系统公告 
	 */
	public void addMsg(){
		
		new Global_msg().set("content",URLDecoder.decode(getPara(0))).save();
		
		System.out.println("++++++++++++++++++++++++++++参数： "+URLDecoder.decode(getPara(0)));
		renderJson();
	}
	/**
	 * @comment 删除系统公告
	 */
	public void delMsgById(){
		System.out.println("公告id"+getParaToLong(0));
		Global_msg.dao.deleteById(getParaToLong(0));
		
		renderJson();
	}
	/**
	 * @comment ajax的动态分页
	 */
	public void ajaxfenye(){
		
		int querypage = getParaToInt(0,1);
		Page<Global_msg> page= (Page<Global_msg>) Global_msg.dao.listMsgs(querypage, 3);
		List<Global_msg> msgs = page.getList();
		int pagecount = Global_msg.dao.getPageCount();
		setAttr("globalmsg", page);
		//setAttr("pagecount", pagecount);
		//setAttr("currentpage",2);
		System.out.println(msgs.size());
		render("/back/globalmsg_list.html");
		
	}
	
	
}