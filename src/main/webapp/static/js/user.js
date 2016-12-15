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
	var uurl="/user/getUsers";
	var utype="get";
	var userData={
		"indexPage":1,
		"pageSize":20
	};
	$.ajaxs(uurl,utype,userData,function(data){
        if(data.errorCode==10000){//成功
        	console.log(data);
        	userManager.userList=data.userList;
        }
	},function(){
		alert("wrong");
	})
}
$(function(){
	userLoad();
})