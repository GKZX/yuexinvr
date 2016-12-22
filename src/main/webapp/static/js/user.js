var userManager=new Vue({
	el:"#userManager",
	data:{
		userList:""
	}
});

//加载用户列表
function userLoad(page , len , successfn , errorfn){
	var uurl="/user/getUsers";
	var utype="get";
	var userData={
		"indexPage":page,
		"pageSize":len
	};
	$.ajax({
		url:"http://localhost:8080/yuexinvr/"+uurl,
		type:utype,
		data:userData,
		dataType:"json",
		async:true,
		timeout:5000,
		success:function(d,page,len){
			successfn(d,page,len);
		},
		error:function(e){
			errorfn(e);
		}
	})
	//$.ajaxs(uurl,utype,userData, successfn, errorfn);
}

$(function(){	
	userLoad(1,20,function(data,page,len){
        if(data.errorCode==10000){//成功
        	//渲染用户数据列表
         	userManager.userList=data.userList;
         	//当用户数量超过一页的数量时显示分页
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
	});
})