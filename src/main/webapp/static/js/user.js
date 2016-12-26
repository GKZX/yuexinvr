var userManager = new Vue({
	el: "#userManager",
	data: {
		userList: "",
		userSize: ""
	}
});

//加载用户列表
function userLoad(page , len){
	var uurl = "/user/getUsers";
	var utype = "get";
	var userData = {
		"indexPage": page,
		"pageSize": len
	};
	$.ajaxs(uurl, utype, userData, function(data){
        if (data.errorCode == 10000){//成功
        	//渲染用户数据列表
         	userManager.userList = data.userList;
         	//当用户数量超过一页的数量时显示分页
         	userManager.userSize = data.userSize;
        	if (userManager.userSize > len){
        		 $('.M-box2').pagination({
                     coping: true,
                     homePage: '首页',
                     endPage: '末页',
                     totalData: size,
                     showData: len,
                     current: page,
                     callback: function (api){
						  //获取点击时的当前页码再重新加载当前页的数据
                           var now = api.getCurrent();
                           userLoad(now,len);
                      }
                 });
        	}	
        }
	},function (){
		alert("服务器错误");
	});
}

$(function (){	
	userLoad(1, 20);
})