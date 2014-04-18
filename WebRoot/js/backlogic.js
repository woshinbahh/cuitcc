/**
 * @author luyi
 * @comment 后台业务逻辑
 */

/**
 * 
 */
/*
 * function putmsg(){ var content = $("#datainput").val(); alert(content); }
 */

function getRootPath() {
	// 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址，如： http://localhost:8083
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名，如：/uimcardprj
	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht);
}

function getfenye(fenyeurl){
	
	$.post( fenyeurl, function(data) {
	  $("#sysmessage").html( data );
	});
}
$(function() {
	// 系统公告模块 发布公告功能
	$("#putglobalmsg").click(function() {
		var msgcontent = encodeURI($("#globalmsgdata").val());

		$.ajax({
			type : "post",
			url : getRootPath() + "/backuserman/addMsg/" + msgcontent,
			dataType : "json",
			success : function() {
				// alert("成功就清空");
				$("#globalmsgdata").val("");
			}
		});
	});

	// 删除系统公告
	$("#datatable_1 tr:not(:first)").find("td:last").click(function() {

		// alert($(this).siblings("input").val());
		var msgId = $(this).siblings("input").val();
		// var msgId = $("#datatable_1
		// tr:not(:first)").find("td:last").siblings("input").val();
		$.ajax({
			type : "post",
			url : getRootPath() + "/backuserman/delMsgById/" + msgId,
			dataType : "json",
			success : function() {
				// do something
				alert("删除公告成功");
			}
		});
	});

	
			
});
