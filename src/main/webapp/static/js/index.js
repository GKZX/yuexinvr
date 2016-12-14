var list=new Vue({
	el:"#listManage",
	data:{
		baseUrl:"video.html?id=",
		indexId:1,
		lists:{}
	},
	methods:{
		goPage:function(){
			//alert(2);
			window.location.href="video.html"
		}
	}
})
function listLoad(){
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":0
	};
	$.ajaxs(curl,ctype,classData,function(data){
		console.log(data);
        if(data.errorCode==10000){//成功
        	list.lists=data.vedioCategoryList;
        	list.indexId=data.vedioCategoryList[0].id;
        }
	},function(){
		alert("wrong");
	})
}
$(function(){
	listLoad();
})
