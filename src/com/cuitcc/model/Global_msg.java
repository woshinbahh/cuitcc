package com.cuitcc.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Db;

@SuppressWarnings("serial")
public class Global_msg extends Model<Global_msg> {

	public static final Global_msg dao = new Global_msg();

	/**
	 * 
	 * @comment 分页查系统消息
	 * @param page_size
	 * @return
	 * @author luyi
 	 */
	public Page<Global_msg> listMsgs(int p, int page_size) {
		System.out.println("huoqu"+p);
		return paginate(p, page_size, "select *", "from global_msg order by id");
		
	}
	/**
	 * @comment 删除指定ID的系统消息
	 * @param globalmsgId 系统公告id
	 * @author luyi
	 * @return void
	 */
	public void delGlobalMsgById(long globalmsgId){
		
		deleteById(globalmsgId);
	}
	
	/**
	 * @comment 删除全部系统公告
	 * @author luyi
	 * @return void
	 */
	public void delAllMsg(){
		Db.query("delete * from global_msg");
	}
	/**
	 * @comment 发布系统公告
	 * @param
	 * @author luyi
	 * @return void
	 */
	public void addGlobalMsg(){
		new Global_msg().set("11", "1").save();
		
	}
	
	/**
	 * 得到页数
	 */
	public int getPageCount(){
		
		int rowcount =Integer.parseInt(Db.query("select count(*) from global_msg").get(0).toString());
		int pagecount = 0;
		if(rowcount%7 == 0){
			pagecount = rowcount/7;
		}else if(rowcount%7 > 0){
			pagecount = rowcount/7+1;
		}
		System.out.println(pagecount+"a"+rowcount/7);
		return pagecount;
		
	}
	/**
	 * 测试用方法
	 * @return
	 */
	public Global_msg findOne(){
		return (Global_msg) findById(1);
	}
	
	
}
