<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>视频管理</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/iconfont.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/main.css" rel="stylesheet" />
	</head>
	<body>
		<div class='video-frame-wrapper' id="videoManager" v-cloak>
			<!--search-box部分-->
			<div class='video-box search-box'>
				<div class='row video-row search-row'>
				  <div class='col-sm-6 col-sm-offset-3'>
					  <form class="bs-example bs-example-form" role="form">
				        <div class="input-group video-input-group">
				            <input type="text" class="form-control video-search" placeholder="twitterhandle">
				            <span class="input-group-addon video-search pointer"><i class='iconfont icon-search'></i>搜索</span>
				        </div>
				      </form>
			      </div>
				</div>
			</div>
			<!--class-box部分-->
			<div class="class-box video-box">
				<div class="row">
					<div class="col-xs-12 video-box-tit">
						共{{message.count}}个视频
					</div>
				</div>
				<div class="clearfix" v-for="c in message.classes">
					<div class="video-box-item" v-for="item in c" track-by="$index">
						<a href="javascript:;">{{item}}</a>
					</div>
				</div>
			</div>
			<!--list-box部分-->
			<div class="list-box">
				<div class="list-delete-box clearfix" v-show="isEdit">
					<div class="list-delete-tab list-box-tab">
						<a href="###" v-on:click="delVideo">删除视频</span>
					</div>
					<div class="list-box-tab">
						<a href="javascript:;" v-on:click="backVideo">退出</a>
					</div>
				</div>
				<div class="clearfix list-choise-box" v-else>
					<div class="list-box-tab">
						<a href="###" v-on:click="editVideo">批量删除</span>
					</div>
					<div class="list-box-tab">
						<a href="upload.html">上传模式</a>
					</div>
				</div>
				
				<div class="list-info clearfix">
					<div class="list-box-item" v-for="list in message.lists"  v-on:click="checkVideo($index)">
					 <a href="javascript:;">
					 	<div class="check-box" v-show="isEdit">
					 		<i class="iconfont icon-gou" v-show="list.checked"></i>
					 	</div>
					 	<div class="video-mask" v-show="isEdit">
					 	</div>
						<div class="video-img">
							<img src="img/video-1.jpg" v-bind:src="list.img">
						</div>
						<div class="video-desc">
							<h3 class="video-desc-tit">{{list.tit}}</h3>
							<ul class="video-desc-list">
								<li>
									<span>视频类型:</span>
									<span class="data">{{list.type}}</span>
								</li>
								<li>
									<span>上传时间:</span>
									<span class="data">{{list.time}}</span>
								</li>
								<li>
									<span>播放量:</span>
									<span class="data">{{list.num}}</span>
								</li>
								<li>
									<span>销售量:</span>
									<span class="data">{{list.sale}}</span>
								</li>
								<li>
									<div class="btn video-edit-btn pull-right"><a href="upload.html">编辑</a></div>
								</li>
							</ul>
						</div>
					 </a>
					</div>
				</div>
			</div>
		</div>
		<script src="js/json.js"></script>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/video.js"></script>
	</body>
</html>
