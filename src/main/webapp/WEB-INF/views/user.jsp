<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户管理</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/main.css" rel="stylesheet" />
	</head>
	<body>
	  <div class="frame-wrapper" id="userManager" v-cloak>
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
		  <tbody v-for="item in userInfo">
		    <tr>
		      <td>{{item.name}}</td>
		      <td>{{item.data}}</td>
		      <td>{{item.phone}}</td>
		      <td>{{item.belief}}</td>
		      <td>{{item.result}}</td>
		      <td>{{item.type}}</td>
		      <td>{{item.record}}</td>
		    </tr>  
		  </tbody>
		</table>
	  </div>
		<script src="js/json.js"></script>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/user.js"></script>
	</body>
</html>