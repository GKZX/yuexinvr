var userManager=new Vue({
	el:"#userManager",
	data:{
		userList:""
	}
})
//加载用户列表
function userLoad(page,len){
	var uurl="/user/getUsers";
	var utype="get";
	var userData={
		"indexPage":page,
		"pageSize":len
	};
	$.ajaxs(uurl,utype,userData,function(data){
        if(data.errorCode==10000){//成功
        	console.log(data);
        	userManager.userList=data.userList;
        	var size=data.userSize;
        	if(size>len){
        		 $('.M-box2').pagination({
                     coping:true,
                     homePage:'首页',
                     endPage:'末页',
                     totalData:size,
                     showData:len,
                     current:page,
                     callback:function(api){
                           var now=api.getCurrent();
                           userLoad(now,len);
                       }
                 });
        	}
        	
        }
	},function(){
		alert("服务器错误");
	})
}
$(function(){	
	userLoad(1,20);
})