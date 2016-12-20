<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title>vr视频上传</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/upload.css" rel="stylesheet" />	
	</head>
	<body>
		<div class="frame-wrapper" id="uploadManage" v-cloak>
			<div class="upload-content">
				<form name="myForm" action="" method="post" enctype="multipart/form-data" class="form-horizontal" role="form" onsubmit="return false;">
				    <div class="form-group">
				  	    <div class="col-xs-4">
						    <div class="upload upload-cover-box">
						    	<div class="upload-cover-preview"><img class="imgpreview-image" v-bind:src="info.vedioImgUrl"></div>
							    <div class="upload-cover-btn upload-btn pointer" id="updataBtn">上传封面</div>
							</div>
							<input type="file" class="uploadFile" id="upImgFile" style="display:none;">
						</div>
				        <label for="firstname" class="col-xs-8 upload-cover-info">图片不超过4M</label>
				    </div>
				    <div class="form-group">
				        <label for="lastname" class="col-xs-2 control-label">视频地址</label>
				        <div class="col-xs-2">
				            <div class="upload-video-btn upload-btn pointer" id="upVideoBtn">选择视频</div>
				        </div>
				        <div class="col-xs-2">
				            <a class="upload-btn upload-view-video pointer" v-bind:href="info.vedioUrl" target="_blank" v-show="info.type==1">查看视频</a>
				        	<input type="file" id="upVideoFile" class="upVideoFile" style="display:none;">
				        </div>
				        <div class="col-xs-6">
				            <div class="progress" v-show="videoProgressShow">
								<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" v-bind:style="{width: videoProgress}">
								    <span class="sr-only">{{videoProgress}} 完成</span>
								</div>
							</div>
							<span class="videoChoise" style="font-size:12px;line-height:30px;">{{vedioInfo}}</span>
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
					        <input type="text" class="form-control video-price-input" placeholder="请输入视频价格" v-show="info.isFree==1" v-model="info.money"><span v-show="info.isFree==1">元</span>
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
				            <input type="submit" class="upload-btn upload-submit-btn" value="提交" v-on:click="upload" v-bind:disabled="disabled"/>
				        </div>
				    </div>
				</form>
				<div class="upInfo" style="display:none;">
				   <span class="accessKeyId">${accessKeyId}</span>
				   <span class="secretAccessKey">${secretAccessKey}</span>
				   <span class="endpoint">${endpoint}</span>
				   <span class="bucket">${bucket}</span>
				</div>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="videoWarn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
								视频仅限于rm,rmvb,wmv,avi,mpg,mpeg,mp4格式!
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
				<div class="modal fade" id="imgWarn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
								图片仅限于png,gif,jpeg,jpg格式!
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
				<div class="modal fade" id="imgSizeWarn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
								上传图片像素必须560像素X360像素以上!
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
				<div class="modal fade" id="imgWidthWarn" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
								上传图片不能超过4M!
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
			</div>
		</div>
		<script src="js/jquery-3.1.1.min.js"></script>
		<script src="js/aliyun-sdk.min.js"></script>
    	<script src="js/vod-sdk-upload.min.js"></script>
		<script src="js/vue.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/common.js"></script>
		<script src="js/upload.js"></script>
	</body>
</html>
