package com.cuitcc.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Tag extends Model<Tag> {

	/**
	 * 主题标签表
	 */
	private static final long serialVersionUID = -6309595397854739532L;
	public static final Tag dao = new Tag();

	public List<Tag> listTagsByPost(Long postId) {
		return find("select * from  tag where post_id = ? limit 5", postId);
	}

}
