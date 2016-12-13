var login=new Vue({
	el:"#loginManage",
	data:{
		isWrong:false
	},
	methods:{
		Submit:function(){
			var self=this;
			var lurl="http://localhost:8080/yuexinvr/login";
			var loginData={
				"userName":$("#userName").val(),
				"password":$("#password").val()
			};
			$.ajaxs(lurl,loginData,function(data){
				alert(0);
				console.log(data);
				if(data.errorCode==20001){//用户名或者密码错误
					self.isWrong=true;
				}else if(data.errorCode==10000){//登陆成功
					self.isWrong=false;
				}
			},function(){
				alert("wrong");
			})
		}
	}
})

