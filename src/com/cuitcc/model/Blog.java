package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

/**
 * Blog model.
 */
@SuppressWarnings("serial")
public class Blog extends Model<Blog> {
	public static final Blog dao = new Blog();

	public Page<Blog> listblogs(int p, int page_size) {
		return paginate(p, page_size, "select *", "from blog order by id asc");
	}
}