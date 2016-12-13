<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登陆页面</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/iconfont.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/login.css" rel="stylesheet" />
	</head>
	<body>
		<div class="login-box" id="loginManage" v-cloak>
			<div class="login-tit-box">
				<h1 class="login-tit">后台管理系统</h1>
				<h2 class="login-sub-tit">Content Manage System</h2>	
			</div>
			<div class="login-info-box">
				<p class="login-warn" v-show="isWrong">你输入的用户名或密码错误</p>
				<div>
					<form class="form-horizontal" role="form">
					    <div class="login-input-group">
					        <span class="col-xs-2 login-input-label iconfont icon-user"></span>
					        <div class="col-xs-10">
					           <input type="text" class="login-input" placeholder="用户名" id="userName">
					        </div>
					    </div>
					    <div class="login-input-group">
					        <span class="col-xs-2 login-input-label iconfont icon-iconfontpassword"></span>
					        <div class="col-xs-10">
					           <input type="password" class="login-input" placeholder="密码" id="password">
					        </div>
					    </div>
					    <a href="##" class="login-forget">忘记密码？</a>
					    <input type="button" class="login-btn" value="立即登陆" id="loginBtn" v-on:click="Submit"/>
					</form>
                </div>
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/common.js"></script>
		<script src="js/login.js"></script>
	</body>
</html>