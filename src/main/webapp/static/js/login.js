var login = new Vue ({
	el: "#loginManage",
	data: {
		isWrong: false
	},
	methods: {
		Submit: function (){
			var self = this;
			var lurl = "login";
			var type = "post";
			var loginData = {
				"userName": $("#userName").val(),
				"password": $("#password").val()
			};
			$.ajaxs(lurl, type, loginData, function (data){
				if (data.errorCode == 20001){//用户名密码错误显示错误提示信息
					self.isWrong = true;
				}else if (data.errorCode == 10000){//正确则直接跳转至主页
					self.isWrong = false;
					window.location.href = "index";
				}
			},function (){
				alert("服务器错误");
			})
		}
	}
})

