<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8" />
		<title>vr-主界面</title>
		<link href="css/bootstrap.css" rel="stylesheet" />
		<link href="css/iconfont.css" rel="stylesheet" />
		<link href="css/common.css" rel="stylesheet" />
		<link href="css/index.css" rel="stylesheet" />
	</head>
	<body>
	  <div class="wrapper">
		<!--上侧导航开始-->
        <nav class="navbar vr-navbar vr-top-navbar" role="navigation">
		    <div class="container-fluid">
		    <div class="navbar-header">
		        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
		                data-target="" id="sideControl">
		            <span class="sr-only">切换导航</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		        </button>
		        <a class="navbar-brand vr-navbar-brand" href="#"><i></i>阅心VR</a>
		    </div>
		    <ul class="nav navbar-nav pull-right">
				<li class="dropdown nav-sub-link">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="iconfont icon-hyyonghu"></i>${sysUser.sysRealName}
						<i class="iconfont icon-arrowDown"></i>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#">修改密码</a></li>
					</ul>
				</li>
				<li class="nav-sub-link"><a href="logout"><i class="iconfont icon-zhuxiao"></i>注销</a></li>
			</ul>
		    </div>
		</nav>
        <!--上侧导航结束-->
        <!--下侧内容开始-->
        <div class='page-wrapper' id="listManage">
		     <!--左侧导航开始-->
		     <div class="collapse navbar-collapse" id="navbar-collapse">
		     <nav class="navbar vr-navbar vr-left-navbar" role="navigation">
			    <ul class="nav side-menu" id="side-menu">
					<li>
						<a class="side-menu-item drop-down-alink collapsed" data-toggle="collapse" href='#videoList'><i class="iconfont icon-shipin"></i>视频管理<b class="iconfont icon-jiantou pull-right list-icon"></b></a>
						<ul class='drop-menu collapse' id='videoList'>
							<li><a href="video.html?id={{item.id}}" target="pageMain" class="alink" v-for="item in lists">{{item.vedioCategoryName}}</a></li>
						</ul>
					</li>
					<!--<li>
						<a class="side-menu-item drop-down-alink collapsed" data-toggle="collapse" href='#dataList'><i class="iconfont icon-data"></i>数据管理<b class="iconfont icon-jiantou pull-right list-icon"></b></a>
						<ul class="drop-menu collapse" id='dataList'>
							<li><a href="userCount.html" target="pageMain" class="alink">用户数据统计</a></li>
							<li><a href="rank.html" target="pageMain" class="alink">视频排行</a></li>
							<li><a href="income.html" target="pageMain" class="alink">收入</a></li>
						</ul>
					</li>-->
					<li>
						<a class="side-menu-item alink" href="user.html" target="pageMain"><i class="iconfont icon-hyyonghu"></i>用户管理</a>
					</li>
				</ul>
			</nav>
			</div>
		    <!--左侧导航结束-->
		    <!--页面内容部分开始-->
		    <div class='page-main' id="pageMain">
		    	<iframe name="pageMain" width="100%" height="100%" v-bind:src="baseUrl+indexId" frameborder="0" data-id="" seamless></iframe>
		    </div>
		    <!--页面内容部分结束-->
        </div>
     </div>
     <script src="js/jquery-3.1.1.min.js"></script>
     <script src="js/vue.js"></script>
     <script src="js/bootstrap.min.js"></script>
     <script src="js/common.js"></script>
     <script src="js/index.js"></script>
	</body>
</html>
