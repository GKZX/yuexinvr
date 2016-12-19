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
				<form name="myForm" action="" method="post" enctype="multipart/form-data" class="form-horizontal" role="form" onsubmit="return false;">
				    <div class="form-group">
				  	    <div class="col-xs-4">
						    <div class="upload upload-cover-box">
						    	<div class="upload-cover-preview"><img src="img/album.png" class="imgpreview-image"></div>
							    <div class="upload-cover-btn upload-btn pointer" id="updataBtn">上传封面</div>
							</div>
							<input type="file" class="uploadFile" id="upImgFile" style="display:none;">
						</div>
				        <label for="firstname" class="col-xs-8 upload-cover-info">560像素  x 360像素以上，不超过4M</label>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频地址</label>
				        <div class="col-xs-4">
				            <div class="upload-video-btn upload-btn pointer" id="upVideoBtn">选择视频</div>
				        </div>
				        <div class="col-xs-6">
				            <span class="videoChoise">{{vedioInfo}}</span>
				        	<input type="file" id="upVideoFile" class="upVideoFile" style="display:none;">
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频名称</label>
				        <div class="col-xs-10">
				            <input type="text" class="form-control" id="lastname" placeholder="请输入视频名称" required v-model="info.vedioName">
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频简介</label>
				        <div class="col-xs-10">
				            <input type="text" class="form-control" id="lastname" placeholder="请输入视频简介" required v-model="info.vedioNotes">
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频类型</label>
				        <div class="col-xs-3">
			                <select class="form-control upload-select" v-model="info.isFree">
						      <option selected value="0">免费</option>
						      <option value="1">收费</option>
						    </select>
				        </div>
				        <div class="col-xs-7">
					        <input type="text" class="form-control video-price-input" placeholder="请输入视频价格" v-show="info.isFree==1" v-model="info.money" required><span v-show="info.isFree==1">元</span>
				        </div>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频分类</label>
				        <div class="col-xs-3">
				            <select class="form-control upload-select" v-model="info.vedioCategoryPId" v-on:change="choise">
						      <option v-for="c in fclass" :value="c.id">{{c.vedioCategoryName}}</option>
						    </select>
				        </div>
				        <div class="col-xs-7">
				            <select class="form-control upload-select" v-show="sclass.length>0" v-model="info.vedioCategoryId">
						      <option v-for="s in sclass" :value="s.id">{{s.vedioCategoryName}}</option>
						    </select>
				        </div>
				    </div>
				    <div class="form-group submit-box">
				        <div class="col-xs-12">
				            <input type="submit" class="upload-btn upload-submit-btn" value="提交" v-on:click="upload" />
				        </div>
				    </div>
				</form>
				<div class="upInfo" style="display:none;">
				   <span class="accessKeyId">${accessKeyId}</span>
				   <span class="secretAccessKey">${secretAccessKey}</span>
				   <span class="endpoint">${endpoint}</span>
				   <span class="bucket">${bucket}</span>
				</div>
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/aliyun-sdk.min.js"></script>
    	<script src="js/vod-sdk-upload.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/common.js"></script>
		<script src="js/upload.js"></script>
	</body>
</html>
