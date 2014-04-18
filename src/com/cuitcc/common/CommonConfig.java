package com.cuitcc.common;

import com.cuitcc.controller.PostController;
import com.cuitcc.controller.UserController;
import com.cuitcc.controller.UserManController;
import com.cuitcc.model.Append;
import com.cuitcc.model.Global_msg;
import com.cuitcc.model.Node;
import com.cuitcc.model.Post;
import com.cuitcc.model.Review;
import com.cuitcc.model.Tab;
import com.cuitcc.model.Tag;
import com.cuitcc.model.User;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * API引导式配置
 */
public class CommonConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("config");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/user", UserController.class);
		me.add("/post", PostController.class);
		me.add("/back", BackIndexController.class);
		me.add("/backuserman", UserManController.class);
		
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.setShowSql(true);
		me.add(arp);
		arp.addMapping("user", User.class);
		arp.addMapping("post", Post.class);
		arp.addMapping("node", Node.class);
		arp.addMapping("tab", Tab.class);
		arp.addMapping("tag", Tag.class);
		arp.addMapping("append", Append.class);
		arp.addMapping("review", Review.class);
		arp.addMapping("global_msg", Global_msg.class);
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {

	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {

	}

	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目 运行此 main
	 * 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}
}
