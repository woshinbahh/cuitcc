package com.cuitcc.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class Node extends Model<Node> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7679587584461095226L;

	public static final Node dao = new Node();

	public List<Node> listNodes(Long tabId) {
		return find(
				"select * from node,nodes_in_tabs where nodes_in_tabs.tab_id = ? and node.id = nodes_in_tabs.node_id",
				tabId);
	}

	public List<Node> listHotNodes() {
		return find("select node.*,t.post_count from node,(select count(*) post_count , node_id from post group by node_id) t where node.id = t.node_id order by post_count limit 20");
	}
	
	public List<Node> litsLastCreate() {

		return find("select * from node order by create_date desc limit 20");
	}

}
