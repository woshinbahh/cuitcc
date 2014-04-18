package com.cuitcc.controller;

import java.util.HashMap;
import java.util.Map;

import com.cuitcc.model.Append;
import com.cuitcc.model.Mention;
import com.cuitcc.model.Node;
import com.cuitcc.model.Post;
import com.cuitcc.model.Review;
import com.cuitcc.model.Site;
import com.cuitcc.model.Tag;
import com.cuitcc.model.User;
import com.jfinal.core.Controller;

/**
 * CommonController
 */
public class PostController extends Controller {

	public void index() {

		long postId = getParaToInt(0);
		// 主题主体
		setAttr("post", Post.dao.getPostById(postId));
		// 主题收藏次数
		setAttr("post_fllowed_count", Post.dao.getPostFllowedCount(postId));

		// 主题标签
		setAttr("tags", Tag.dao.listTagsByPost(postId));

		// 主题附加信息
		setAttr("appends", Append.dao.listAppendByPost(postId));

		// 主题回复列表
		setAttr("reviewPage",
				Review.dao.listReviewByPost(postId, getParaToInt(1, 1), 15));

		// 最后回复时间
		setAttr("last_review_time", Review.dao.getlastReviewTimeForPost(postId));

		// 当前登录用户信息
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("user", User.dao.findById(1L));
		userInfo.put("unread_review_count",
				Mention.dao.getUnreadReviewCountByUser(1L));
		userInfo.put("unread_mention_count",
				Mention.dao.getUnreadMentionCountByUser(1L));
		userInfo.put("fllowed_node_count", User.dao.getFllowedNodes(1L));
		userInfo.put("fllowed_post_count", User.dao.getFllowedPosts(1L));
		userInfo.put("fllowed_user_count", User.dao.getFllowedUsers(1L));
		setAttr("user_info", userInfo);

		// 今日最热主题
		setAttr("hot_posts", Post.dao.litsTodaysHotPosts());

		// 最热节点
		setAttr("hot_nodes", Node.dao.listHotNodes());

		// 最近新增节点
		setAttr("last_nodes", Node.dao.litsLastCreate());

		// 站点发展状况
		setAttr("user_count", Site.dao.getUserCount());
		setAttr("post_count", Site.dao.getPostCount());
		setAttr("review_count", Site.dao.getReviewCount());
		render("/common/post.html");
	}
}
