<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>vr视频上传</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/main.css" rel="stylesheet" />	
	</head>
	<body>
		<div class="frame-wrapper" id="uploadManage" v-cloak>
			<div class="upload-content">
				<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
				    <div class="form-group">
				  	    <div class="col-xs-4">
						    <div class="upload upload-cover-box">
						    	<div class="upload-cover-preview"><img src="img/album.png" class="imgpreview-image"></div>
							    <div class="upload-cover-btn upload-btn pointer" id="updataBtn">上传封面</div>
							</div>
							<input type="file" class="uploadFile" style="display: none;">
						</div>
				        <label for="firstname" class="col-xs-8 upload-cover-info">560像素  x 360像素以上，不超过4M</label>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频地址</label>
				        <div class="col-xs-10">
				            <div class="upload-video-btn upload-btn pointer" id="upVideoBtn">选择视频</div>
				        </div>
				        <input type="file" class="upVideoFile" style="display: none;">
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频名称</label>
				        <div class="col-xs-10">
				            <input type="text" class="form-control" id="lastname" placeholder="请输入视频名称">
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频简介</label>
				        <div class="col-xs-10">
				            <input type="text" class="form-control" id="lastname" placeholder="请输入视频简介">
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频类型</label>
				        <div class="col-xs-3">
			                <select class="form-control upload-select" v-model="isfee">
						      <option selected value="false">免费</option>
						      <option value="true">收费</option>
						    </select>
				        </div>
				        <div class="col-xs-7">
					        <input type="text" class="form-control video-price-input" placeholder="请输入视频价格" v-show="isfee=='true'"><span v-show="isfee=='true'">元</span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频分类</label>
				        <div class="col-xs-3">
				            <select class="form-control upload-select">
						      <option>宗教文化</option>
						      <option>旅游风景</option>
						      <option>恐怖惊悚</option>
						      <option>4</option>
						      <option>5</option>
						    </select>
				        </div>
				        <div class="col-xs-7">
				            <select class="form-control upload-select">
						      <option>基督教</option>
						      <option>2</option>
						      <option>3</option>
						      <option>4</option>
						      <option>5</option>
						    </select>
				        </div>
				    </div>
				    <div class="form-group submit-box">
				        <div class="col-xs-12">
				            <input type="submit" class="upload-btn upload-submit-btn" value="提交" />
				        </div>
				    </div>
				</form>
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/upload.js"></script>
	</body>
</html>