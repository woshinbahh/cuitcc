package com.cuitcc.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Append extends Model<Append> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4480211985767785581L;
	/**
	 * 主题标签表
	 */
	public static final Append dao = new Append();

	public List<Append> listAppendByPost(Long postId) {
		return find(
				"select * from  append where post_id = ? order by create_date",
				postId);
	}

}
