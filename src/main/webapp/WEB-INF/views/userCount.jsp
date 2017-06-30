<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>用户数据统计</title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, user-scalable=no">
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/data.css" rel="stylesheet" />
	</head>
	<body>
		<div class="frame-wrapper">
			<div class="data-tit-box">
				<p class="data-register">
					总注册用户<span class="num">2003</span>人
				</p>
				<div class="clearfix">
					<div class="pull-left">
						<h1 class='data-tit'>用户数</h1>
					</div>
					<div class="pull-left data-date-choise">
						<select class="form-control date-select">
						    <option>今日</option>
						    <option>本月</option>
					    </select>
					</div>
					<div class="pull-left">
					    
					</div>
				</div>
			</div>
			<div class="data-graph-box">
				<div class="data-graph" id="userGraph">
				
			    </div>
			</div>	
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/echarts.common.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/userCount.js"></script>
	</body>
</html>