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
				console.log(data);
				if(data.errorCode==20001){//鐢ㄦ埛鍚嶆垨鑰呭瘑鐮侀敊璇�
					self.isWrong=true;
				}else if(data.errorCode==10000){//鐧婚檰鎴愬姛
					self.isWrong=false;
					//window.location.href=""
				}
			},function(){
				alert("wrong");
			})
		}
	}
})

