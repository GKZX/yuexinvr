var login=new Vue({
	el:"#loginManage",
	data:{
		isWrong:false
	},
	methods:{
		Submit:function(){
			var self=this;
			var lurl="http://192.168.0.139:8080/yuexinvr/login";
			var loginData={
				"userName":$("#userName").val(),
				"password":$("#password").val()
			};
			$.ajax({
				url:lurl,
				type:"post",
				data:loginData,
				dataType:"json",
				async:true,
				timeout:2000,
				success:function(d){
					alert(0);
				},
				error:function(e){
					alert(1);
				}
			})
//			$.ajaxs(lurl,loginData,function(data){
//				alert(0);
//				console.log(data);
//				if(data.errorCode==20001){//用户名或者密码错误
//					self.isWrong=true;
//				}else if(data.errorCode==10000){
//					self.isWrong=false;
//				}
//			},function(){
//				alert("wrong");
//				self.isWrong=true;
//				console.log(self.isWrong);
//			})
		}
	}
})

