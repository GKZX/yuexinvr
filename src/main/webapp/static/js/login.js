var login=new Vue({
	el:"#loginManage",
	data:{
		isWrong:false
	},
	methods:{
		Submit:function(){
			var self=this;
			var lurl="login";
			var type="post";
			var loginData={
				"userName":$("#userName").val(),
				"password":$("#password").val()
			};
			$.ajaxs(lurl,type,loginData,function(data){
				if(data.errorCode==20001){//用户名密码错误
					self.isWrong=true;
				}else if(data.errorCode==10000){//正确
					self.isWrong=false;
					window.location.href="index";
				}
			},function(){
				alert("服务器错误");
			})
		}
	}
})

