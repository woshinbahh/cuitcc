package com.cuitcc.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Tab extends Model<Tab> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679587584461095226L;

	public static final Tab dao = new Tab();

	public List<Tab> listAllTabs() {
		return Tab.dao.find("select * from tab");
	}

	public List<Tab> listTabsIndex() {
		return Tab.dao.find("select * from tab where in_main_menu = 1");
	}

}
