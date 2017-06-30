<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>视频排行</title>
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, user-scalable=no">
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/rank.css" rel="stylesheet" />
	</head>
	<body>
		<div class="frame-wrapper">
			<div class="row rank-box">
				<div class="col-sm-6 rank-side-box">
					<div class="clearfix rank-tit-box">
						<div class="pull-left">
							<h1 class='rank-tit'>视频播放排行</h1>
						</div>
						<div class="pull-left rank-date-choise">
							<select class="form-control data-select">
							    <option>今日</option>
							    <option>本月</option>
						    </select>
						</div>
						<div class="pull-left">
						    
						</div>
					</div>
					<ul class="rank-list">
						<li class="rank-list-item clearfix">
							<div class="rank-list-order pull-left hot">1</div>
							<div class="rank-list-cover pull-left">
							    <img src="img/video-1.jpg"/>
							</div>
							<div class="rank-list-info pull-left">
								<h2 class="rank-list-name">伊利奥斯</h2>
								<p class="rank-list-desc">播放量：20.2万</p>
							</div>
						</li>
					</ul>
				</div>
				<div class="col-sm-6 rank-side-box">
		        
				</div>
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/rank.js"></script>
	</body>
</html>
