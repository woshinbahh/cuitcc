package com.cuitcc.model;

import java.sql.Timestamp;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Review extends Model<Review> {

	private static final long serialVersionUID = 7679587584461095226L;

	public static final Review dao = new Review();

	/**
	 * 通过Tab获取分页获取主题列表
	 * 
	 * @param postId
	 * @param p
	 * @param page_size
	 * @return
	 */
	public Page<Review> listReviewByPost(long postId, int p, int page_size) {
		String select = "select review.*,user.id user_id,user.name user_name,user.email user_email ,user.avatar user_avatar";
		String sqlExceptSelect = "from review,user where review.post_id = ? and user.id = review.user_id order by  review.create_date ";
		return paginate(p, page_size, select, sqlExceptSelect, postId);
	}

	public Timestamp getlastReviewTimeForPost(long postId) {
		return Db
				.queryTimestamp(
						"select create_date from review where post_id = ? order by  create_date desc limit 1",
						postId);
	}

	public Page<Review> listReviewForUser(long userId, int p, int page_size) {

		String select = " select review . *,  post.title post_title, user.id post_user_id, user.name post_user_name ";
		String sqlExceptSelect = " from  review,  post,  user "
				+ " where  review.user_id = ? "
				+ "         and review.post_id = post.id "
				+ "         and post.user_id = user.id "
				+ " order by create_date desc ";
		return paginate(p, page_size, select, sqlExceptSelect, userId);

	}
}
