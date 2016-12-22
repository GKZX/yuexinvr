<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户管理</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/pagination.css" rel="stylesheet"/>
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/user.css" rel="stylesheet" />
	</head>
	<body>
	  <div class="frame-wrapper" id="userManager" v-cloak>
	    <div class="frame-content">
			<table class="table table-bordered user-table">
			  <thead>
			    <tr>
			      <th>用户名</th>
			      <th>注册日期</th>
			      <th>联系方式</th>
			      <th>宗教信仰</th>
			      <th>历史心理评估结果</th>
			      <th>建议推送类型</th>
			      <th>用户购买记录</th>
			    </tr>
			  </thead>
			  <tbody v-for="item in userList">
			    <tr>
			      <td>{{item.userName}}</td>
			      <td>{{item.addTime | formatDate}}</td>
			      <td>{{item.phone}}</td>
			      <td>无</td>
			      <td>无</td>
			      <td>无</td>
			      <td>无</td>
			    </tr>  
			  </tbody>
			</table>
		</div>
		<div class="M-box2 page-box"></div>
	  </div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/jquery.pagination.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/common.js"></script>
		<script src="js/user.js"></script>
	</body>
</html>
