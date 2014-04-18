package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class Mention extends Model<Mention> {

	private static final long serialVersionUID = 7679587584461095226L;

	public static final Mention dao = new Mention();

	public Long getUnreadReviewCountByUser(Long userId) {
		return Db
				.queryLong(
						"select count(*) from mention where user_id = ? and  status = 1 and type = 1",
						userId);
	}

	public Long getUnreadMentionCountByUser(Long userId) {
		return Db
				.queryLong(
						"select count(*) from mention where user_id = ? and  status = 1 and type = 2",
						userId);
	}
}
