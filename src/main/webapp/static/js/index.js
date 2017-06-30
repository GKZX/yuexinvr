var list=new Vue({
	el:"#listManage",
	data:{
		baseUrl:"video.html?id=",
		indexId:1,
		lists:{}
	}
})

//视频数据列表加载
function listLoad(){
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":0
	};
	$.ajaxs(curl,ctype,classData,function(data){
        if(data.errorCode==10000){//成功
        	list.lists=data.vedioCategoryList;
			//大类ID区分不同分类页数据
        	list.indexId=data.vedioCategoryList[0].id;
        }
	},function(){
		alert("服务器错误");
	})
}

$(function(){	
	//加载视频列表
	listLoad();

	//列表图标展开收缩切换
	$(".drop-down-alink").click(function(){
		if($(this).hasClass("collapsed")){//处于收缩状态点击展开
			$(this).children(".list-icon").removeClass("icon-jiantou").addClass("icon-arrowDown");
		}else{//处于展开状态点击收缩
			$(this).children(".list-icon").removeClass("icon-arrowDown").addClass("icon-jiantou");
		}
	})
	
	//响应式按钮控制侧滑菜单的显示隐藏
	$("#sideControl").click(function(){
		if($(this).hasClass("collapsed")){//处于收缩状态点击展开
			$(this).removeClass("collapsed");
			$("#navbar-collapse").css("display","block");
			$("#pageMain").css("margin-left","200px");
		}else{
			$(this).addClass("collapsed");
			$("#navbar-collapse").css("display","none");
			$("#pageMain").css("margin-left","0px");
		}
	})

	//窗口大小改变时调整内层窗口距离左边的距离
	$(window).resize(function(){
		if($(window).width()>750){
			$("#pageMain").css("margin-left","200px");
		}else{
			$("#pageMain").css("margin-left","0px");
		}
		
	})
})

