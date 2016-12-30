<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
	<head>
		<meta charset="UTF-8">
		<title>视频管理</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/iconfont.css" rel="stylesheet" />
		<link href="css/pagination.css" rel="stylesheet"/>
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/video.css" rel="stylesheet" />
	</head>
	<body>
		<div class='video-frame-wrapper' id="videoManager" v-cloak>
		    <div class="frame-content">
				<!--search-box部分-->
				<div class='video-box search-box'>
					<div class='row video-row search-row'>
					  <div class='col-sm-6 col-sm-offset-3'>
						  <form class="bs-example bs-example-form" role="form">
					        <div class="input-group video-input-group">
					            <input type="text" class="form-control video-search" placeholder="输入搜索的视频" v-model="searchCriteria">
					            <span class="input-group-addon video-search pointer" v-on:click="search"><i class='iconfont icon-search'></i>搜索</span>
					        </div>
					      </form>
				      </div>
					</div>
				</div>
				<!--class-box部分-->
				<div class="class-box video-box">
					<div class="row">
						<div class="col-xs-12 video-box-tit">
							共{{message.vedioSize}}个视频
						</div>
					</div>
					<div class="clearfix video-class">
					   <div class="video-box-item sort-item">
					      <a href="javascript:;" v-on:click="sort(0),active($event)">全部视频</a>
					   </div>
					   <div class="video-box-item sort-item">
					      <a href="javascript:;" v-on:click="sort(1),active($event)">播放量</a>
					   </div>
					   <div class="video-box-item active sort-item">
					      <a href="javascript:;" v-on:click="sort(2),active($event)">最近上传</a>
					   </div>
					   <div class="video-box-item sort-item">
					      <a href="javascript:;" v-on:click="sort(3),active($event)">销售量</a>
					   </div>
					</div>
					<div class="clearfix video-class">
					   <div class="video-box-item active class-item" v-show="sclass.length>0">
					      <a href="javascript:;" v-bind:id="fid" v-on:click="choiseClass($event),active($event)">全部分类</a>
					   </div>
					   <div class="video-box-item class-item" v-for="item in sclass" track-by="$index">
						  <a href="javascript:;" v-bind:id="item.id" v-on:click="choiseClass($event),active($event)">{{item.vedioCategoryName}}</a>
					   </div>
					</div>
				</div>
				<!--list-box部分-->
				<div class="list-box">
					<div class="list-delete-box clearfix" v-show="isDel">
						<div class="list-delete-tab list-box-tab">
							<a href="###" v-on:click="delVideo">删除视频</span>
						</div>
						<div class="list-box-tab">
							<a href="javascript:;" v-on:click="backVideo">退出</a>
						</div>
					</div>
					<div class="clearfix list-choise-box" v-else>
						<div class="list-box-tab">
							<a href="###" v-on:click="delModel">批量删除</a>
						</div>
						<div class="list-box-tab">
							<a href="upload.html?bigId={{id}}&type=0">上传模式</a>
						</div>
					</div>
					
					<div class="list-info clearfix">
					    <template v-if="message.vedioSize > 0"> 
							<div class="list-box-item" v-for="list in message.vedioList"  v-on:click="checkVideo($index)" v-bind:id="list.id">
							 <a href="javascript:;">
							 	<div class="check-box" v-show="isDel">
							 		<i class="iconfont icon-gou" v-show="list.checked"></i>
							 	</div>
							 	<div class="video-mask" v-show="isDel">
							 	</div>
								<div class="video-img">
									<img src="img/video-1.jpg" v-bind:src="list.vedioImgUrl">
								</div>
								<div class="video-desc">
									<h3 class="video-desc-tit">{{list.vedioName}}</h3>
									<ul class="video-desc-list">
										<li>
											<span>视频类型:</span>
											<span class="data">{{list.isFree | formatType}}</span>
										</li>
										<li>
											<span>上传时间:</span>
											<span class="data">{{list.addTime | formatDate}}</span>
										</li>
										<li>
											<span>播放量:</span>
											<span class="data">{{list.playAmount}}</span>
										</li>
										<li>
											<span>销售量:</span>
											<span class="data">0</span>
										</li>
										<li>
											<div class="btn video-edit-btn pull-right"><a href="upload.html?id={{list.id}}&type=1" class="video-edit-link">编辑</a></div>
										</li>
									</ul>
								</div>
							 </a>
							</div>
						</template>
						<template v-else>
						     <p class="empty-describe">暂无相关视频</p>
						</template>
					</div>
				</div>
			</div>	
			<div class="M-box2 page-box"></div>
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								确认框
							</h4>
						</div>
						<div class="modal-body">
							确认删除以下勾选的视频？
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消
							</button>
							<button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="confirmDel">
								确定
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="choiseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="myModalLabel">
								警告框
							</h4>
						</div>
						<div class="modal-body">
							  请先选择要删除的视频！
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/jquery.pagination.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/video.js"></script>
	</body>
</html>
