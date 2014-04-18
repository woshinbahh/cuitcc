package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Db;

@SuppressWarnings("serial")
public class User extends Model<User> {

	public static final User dao = new User();

	public Page<User> listUsers(int p, int page_size) {

		return paginate(p, page_size, "select *", "from user order by id");

	}

	public Long getFllowedNodes(Long userId) {
		return Db.queryLong(
				"select count(*) from nodes_for_users where user_id = ? ",
				userId);
	}

	public Long getFllowedPosts(Long userId) {
		return Db.queryLong(
				"select count(*) from posts_for_users where user_id = ? ",
				userId);
	}

	public Long getFllowedUsers(Long userId) {
		return Db.queryLong(
				"select count(*) from stars where user_id_host = ? ", userId);
	}

	public User getUserById(long userId) {
		return findById(userId);
	}
}
