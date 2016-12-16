var userManager=new Vue({
	el:"#userManager",
	data:{
		userList:""
	},
	methods:{
		
	}
})
//加载用户列表
function userLoad(){
	var showData=20;
	var uurl="/user/getUsers";
	var utype="get";
	var userData={
		"indexPage":1,
		"pageSize":showData
	};
	$.ajaxs(uurl,utype,userData,function(data){
        if(data.errorCode==10000){//成功
        	console.log(data);
        	userManager.userList=data.userList;
        	 $('.M-box2').pagination({
                 coping:true,
                 homePage:'首页',
                 endPage:'末页',
                 totalData:0,
                 showData:showData,
             });
        }
	},function(){
		alert("wrong");
	})
}
$(function(){	
	userLoad();
})