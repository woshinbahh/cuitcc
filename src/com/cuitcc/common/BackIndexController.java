package com.cuitcc.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cuitcc.model.Global_msg;
import com.cuitcc.model.Mention;
import com.cuitcc.model.Node;
import com.cuitcc.model.Post;
import com.cuitcc.model.Site;
import com.cuitcc.model.Tab;
import com.cuitcc.model.User;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;

/**
 * CommonController
 */
public class BackIndexController extends Controller {

	public void index() {

		//系统公告
		/*
		Global_msg globalMsg = Global_msg.dao.findOne();
		setAttr("globalmsg", globalMsg);
		*/
		//分页数据 默认显示第一页
		Page<Global_msg> page= (Page<Global_msg>) Global_msg.dao.listMsgs(1,3);
		List<Global_msg> msgs = page.getList();
		int pagecount = Global_msg.dao.getPageCount();
		setAttr("globalmsg", page);
		setAttr("pagecount", pagecount);
		setAttr("currentpage",1);
		
		//用户管理
		User user = User.dao.findById(1);
		setAttr("user", user);
		
		//帖子管理
		
		//操作记录
		
		//栏目管理
		
		//节点管理
		
		//系统日志
		
		
		render("/back/index.html");
		
	}
}
