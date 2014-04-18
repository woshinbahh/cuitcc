/**
 * @author luyi
 * @comment 后台界面效果js代码
 */
$(function(){
		   /**导航右边图标切换js*/
	$("#block6").mouseover(
					   function(){
								$("#block6").css("background","url(image/block6_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block6").mouseout(
					   function(){
								$("#block6").css("background","url(image/block6.png)");
								//alert("dsad");
						});
	
	$("#block5").mouseover(
					   function(){
								$("#block5").css("background","url(image/block5_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block5").mouseout(
					   function(){
								$("#block5").css("background","url(image/block5.jpg)");
								//alert("dsad");
						});
	$("#block4").mouseover(
					   function(){
								$("#block4").css("background","url(image/block4_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block4").mouseout(
					   function(){
								$("#block4").css("background","url(image/block4.png)");
								//alert("dsad");
						});
	
	$("#block3").mouseover(
					   function(){
								$("#block3").css("background","url(image/block3_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block3").mouseout(
					   function(){
								$("#block3").css("background","url(image/block3.png)");
								//alert("dsad");
						});
	
	$("#block2").mouseover(
					   function(){
								$("#block2").css("background","url(image/block2_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block2").mouseout(
					   function(){
								$("#block2").css("background","url(image/block2.png)");
								//alert("dsad");
						});
	
	
	$("#block1").mouseover(
					   function(){
								$("#block1").css("background","url(image/block1_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block1").mouseout(
					   function(){
								$("#block1").css("background","url(image/block1.png)");
								//alert("dsad");
						});
	
	$("#block0").mouseover(
					   function(){
								$("#block0").css("background","url(image/block0_2.jpg)");
								//$("#block6").slideToggle();
						});
	
	$("#block0").mouseout(
					   function(){
								$("#block0").css("background","url(image/block0.png)");
								//alert("dsad");
						});
	
	/**
		实现页面平滑滚动到相关功能块的js*/
	$("#block6").click(
					  function(){
							$("html,body").animate({scrollTop:"0px"},'slow');
							return false;	   
					});
	
	$("#block5").click(
					  function(){
							$("html,body").animate({scrollTop:"590px"},'slow');
							return false;	   
					});
	$("#block4").click(
					  function(){
							$("html,body").animate({scrollTop:"1130px"},'slow');
							return false;	   
					});
	$("#block3").click(
					  function(){
							$("html,body").animate({scrollTop:"1673px"},'slow');
							return false;	   
					});
	$("#block2").click(
					  function(){
							$("html,body").animate({scrollTop:"2216px"},'slow');
							return false;	   
					});
	$("#block1").click(
					  function(){
							$("html,body").animate({scrollTop:"3302px"},'slow');
							return false;	   
					});
	$("#block0").click(
					  function(){
							$("html,body").animate({scrollTop:"2459px"},'slow');
							return false;	   
					});
	
	/**
	 * 系统公告块相关Js
	 */
	/*
	 * 实现鼠标移动到相应行 在右边显示该系统公告相关内容
	 */
	$("#datatable_1 tr:not(:first)").mouseover(
									function(){
										//alert("该行被点击");
										var content = $(this).find("td:eq(1)").html();
										$('#showsysmessage').html(content);
									}
	);
	
		
});



			
