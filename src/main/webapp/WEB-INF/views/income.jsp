<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>收入</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/data.css" rel="stylesheet" />
	</head>
	<body>
		<div class="frame-wrapper">
			<div class="clearfix data-tit-box">
				<div class="pull-left">
					<h1 class='data-tit'>收入</h1>
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
			<div class="data-graph-box">
				<div class="data-graph" id="incomeGraph">
				
			    </div>
			</div>	
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/echarts.common.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/income.js"></script>
	</body>
</html>
