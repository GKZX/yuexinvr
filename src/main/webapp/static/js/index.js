var list=new Vue({
	el:"#listManage",
	data:{
		baseUrl:"video.html?id=",
		indexId:1,
		lists:{}
	}
})

function listLoad(){
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":0
	};
	$.ajaxs(curl,ctype,classData,function(data){
        if(data.errorCode==10000){//成功
        	list.lists=data.vedioCategoryList;
        	list.indexId=data.vedioCategoryList[0].id;
        }
	},function(){
		alert("服务器错误");
	})
}

$(function(){
	
	//加载列表
	listLoad();
	//列表图片展开收缩切换
	$(".drop-down-alink").click(function(){
		if($(this).hasClass("collapsed")){//收缩然后展开
			$(this).children(".list-icon").removeClass("icon-jiantou").addClass("icon-arrowDown");
		}else{
			$(this).children(".list-icon").removeClass("icon-arrowDown").addClass("icon-jiantou");
		}
	})
	//响应式按钮控制侧滑菜单的显示隐藏
	$("#sideControl").click(function(){
		if($(this).hasClass("collapsed")){//收缩然后展开
			$(this).removeClass("collapsed");
			$("#navbar-collapse").css("display","block");
			$("#pageMain").css("margin-left","200px");
		}else{
			$(this).addClass("collapsed");
			$("#navbar-collapse").css("display","none");
			$("#pageMain").css("margin-left","0px");
		}
	})
	$(window).resize(function(){
		if($(window).width()>750){
			console.log($(window).width());
			$("#pageMain").css("margin-left","200px");
		}else{
			$("#pageMain").css("margin-left","0px");
		}
		
	})
})