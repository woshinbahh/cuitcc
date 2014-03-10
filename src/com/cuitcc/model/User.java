package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class User extends Model<User> {

	public static final User dao = new User();

	public Page<User> listUsers(int p, int page_size) {

		return paginate(p, page_size, "select *", "from user order by id asc");

	}
}
