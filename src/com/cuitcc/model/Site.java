package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class Site extends Model<Site> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679587584461095226L;

	public static final Site dao = new Site();

	public Long getUserCount() {
		return Db.queryLong("select count(*) from user");
	}

	public Long getPostCount() {
		return Db.queryLong("select count(*) from post");
	}

	public Long getReviewCount() {
		return Db.queryLong("select count(*) from review");

	}

}
