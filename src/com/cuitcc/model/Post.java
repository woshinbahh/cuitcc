package com.cuitcc.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class Post extends Model<Post> {

	private static final long serialVersionUID = 7679587584461095226L;

	public static final Post dao = new Post();

	/**
	 * 
	 * @param nodeId
	 * @param p
	 * @param page_size
	 * @return
	 */
	public Page<Post> listPostsByNode(long nodeId, int p, int page_size) {
		String select = " select "
				+ "     c . *, "
				+ "     (case "
				+ "         when last_review_create_date is not null then last_review_create_date "
				+ "         else create_date end) last_date, "
				+ "     last_review_user_name, last_review_user_id";
		String sqlExceptSelect = " from "
				+ "     (select "
				+ "         a . *, review_count "
				+ "     from "
				+ "         (select "
				+ "             post.*, "
				+ "             node.name node_name, "
				+ "             user.avatar user_avatar, "
				+ "             user.email user_email, "
				+ "             user.name user_name "
				+ "     from "
				+ "         post, user, node "
				+ "     where "
				+ "         post.user_id = user.id "
				+ "             and post.node_id = node.id) a "
				+ "     left join (select "
				+ "         post_id, count(*) review_count "
				+ "     from "
				+ "         review "
				+ "     group by post_id) b ON a.id = b.post_id) c "
				+ "         left join  (select "
				+ "         review.create_date last_review_create_date, "
				+ "             user.name last_review_user_name, "
				+ "             user.id last_review_user_id, "
				+ "             post.id post_id  from "
				+ "         review, user, post where "
				+ "         review.post_id = post.id "
				+ "             and user.id = review.user_id "
				+ "     order by review.create_date desc "
				+ "     limit 1) d ON c.id = d.post_id where "
				+ "     c.node_id = ?  order by last_date desc ";
		return paginate(p, page_size, select, sqlExceptSelect, nodeId);
	}

	public List<Post> litsTodaysHotPosts() {
		String sql = " select  post . *, "
				+ "     b.review_count review_count, "
				+ "     user.id user_id,  user.name user_name, "
				+ "     user.email user_email, "
				+ "     user.avatar user_avatar  from post, "
				+ "     user, (select "
				+ "         count(*) review_count, post_id from "
				+ "         review where "
				+ "         date(review.create_date) = curdate() "
				+ "     group by post_id) b where "
				+ "     post.user_id = user.id "
				+ "         and post.id = b.post_id "
				+ " order by review_count desc limit 10 ";
		return find(sql);
	}

	public Post getPostById(Long postId) {
		String sql = " select post . *, "
				+ "     user.name user_name, user.id user_id, "
				+ "     user.email user_email, "
				+ "     user.avatar user_vatar, node.id node_id, "
				+ "     node.name node_name from post, "
				+ "     user, node where "
				+ "     post.id = ? and user.id = post.user_id "
				+ "         and node.id = post.node_id ";

		return findFirst(sql, postId);
	}

	public long getPostFllowedCount(Long postId) {
		return Db.queryLong(
				"select count(*) from posts_for_users where post_id = ?",
				postId);
	}

	public Page<Post> listPostsByUser(Long userId, int p, int page_size) {
		String select = " select      c . *, last_review_user_name, last_review_user_id";
		String sqlExceptSelect = " from "
				+ "     (select "
				+ "         a . *, review_count "
				+ "     from "
				+ "         (select "
				+ "             post.*, "
				+ "             node.name node_name, "
				+ "             user.avatar user_avatar, "
				+ "             user.email user_email, "
				+ "             user.name user_name "
				+ "     from "
				+ "         post, user, node "
				+ "     where "
				+ "         post.user_id = user.id "
				+ "             and post.node_id = node.id) a "
				+ "     left join (select "
				+ "         post_id, count(*) review_count "
				+ "     from "
				+ "         review "
				+ "     group by post_id) b ON a.id = b.post_id) c "
				+ "         left join  (select "
				+ "         review.create_date last_review_create_date, "
				+ "             user.name last_review_user_name, "
				+ "             user.id last_review_user_id, "
				+ "             post.id post_id  from "
				+ "         review, user, post where "
				+ "         review.post_id = post.id "
				+ "             and user.id = review.user_id "
				+ "     order by review.create_date desc "
				+ "     limit 1) d ON c.id = d.post_id where "
				+ "     c.user_id = ?  order by create_date desc ";
		return paginate(p, page_size, select, sqlExceptSelect, userId);

	}

	public Page<Post> listPostsForUser(Long userId, int p, int page_size) {
		String select = " select c . *, last_review_user_name, last_review_user_id";
		String sqlExceptSelect = " from "
				+ "     (select "
				+ "         a . *, review_count "
				+ "     from "
				+ "         (select "
				+ "             post.*, "
				+ "             node.name node_name"
				+ "     from "
				+ "         post, node "
				+ "     where post.node_id = node.id) a "
				+ "     left join (select "
				+ "         post_id, count(*) review_count "
				+ "     from "
				+ "         review "
				+ "     group by post_id) b ON a.id = b.post_id) c "
				+ "         left join  (select "
				+ "         review.create_date last_review_create_date, "
				+ "             user.name last_review_user_name, "
				+ "             user.id last_review_user_id,"
				+ "             post.id post_id  from "
				+ "         review, user, post where "
				+ "         review.post_id = post.id "
				+ "             and user.id = review.user_id "
				+ "     order by review.create_date desc "
				+ "     limit 1) d ON c.id = d.post_id where "
				+ "     c.user_id = ?  order by create_date desc ";
		return paginate(p, page_size, select, sqlExceptSelect, userId);

	}
 
}
