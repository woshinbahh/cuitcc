package com.cuitcc.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cuitcc.model.Mention;
import com.cuitcc.model.Node;
import com.cuitcc.model.Post;
import com.cuitcc.model.Site;
import com.cuitcc.model.Tab;
import com.cuitcc.model.User;
import com.jfinal.core.Controller;

/**
 * CommonController
 */
public class IndexController extends Controller {

	public void index() {

		// 全部节点
		List<Tab> tabs = Tab.dao.listTabsIndex();
		for (Tab tab : tabs) {
			List<Node> allNodes = Node.dao.listNodes(tab.getLong("id"));
			tab.put("nodes", allNodes);
		}
		setAttr("tabs", tabs);
		setAttr("nodes", Node.dao.listNodes(1L));

		// 主题列表
		setAttr("postPage", Post.dao.listPostsByNode(1, getParaToInt(0, 1), 5));

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
		render("/common/index.html");
	}
}
