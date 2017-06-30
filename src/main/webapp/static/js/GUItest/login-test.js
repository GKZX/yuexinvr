
casper.test.begin('login in vr in a correct account ', 5, function(test){
	casper.options.viewportSize = {width:1366, height:1200};
	casper.options.loadImages=true;
	/*
	     正常登陆状态检测，登陆成功后跳转至首页
	     测试账号（可用）：
	     'userName': 'admin',
		 'password': 'admin123456'
    */	
	
	//进入登陆页
	casper.start('http://localhost:8080/yuexinvr/showLogin');
	
	//等待请求成功后进行登陆信息填充，填好后不自动提交
	casper.waitFor(function check(){
		return test.assertHttpStatus(200,'http success');
	}, function then() {
		if (this.exists('div[id="loginBox"]')) {
			this.fill('div[id="loginBox"]', {
				'userName': 'admin',
				'password': 'admin123456'
			},false);
		}	
	});
	
	//填好后点击提交按钮进行提交
	casper.then(function() {
		if (this.exists('input[class="login-btn"]')) {
			this.click('input[class="login-btn"]');
			this.echo('login...');
		}	
	});
	
	//提交后等待跳转到首页后判断是否请求成功
	casper.waitForUrl('http://localhost:8080/yuexinvr/index', function() {
		this.echo('jump to index successfully');
		test.assertHttpStatus(200,'http success');	
		this.careSelector('../../img/GUItest/login-success.png', 'html');
	});
	
	 casper.thenEvaluate(function() {
			document.getElementById('test2').click();
	 },'sheldon.cooper', 'b4z1ng4');
	 
	 casper.wait(500, function() {
		 this.captureSelector('../../img/GUItest/now.png','html');
	 });
	/*
	   批量删除模式检测，分三种情况：
	   1.未勾选任何视频就点击删除按钮,出现警告框  状态截图保存为deleteNoVideo.png
	   2.勾选了相应要删除的视视频并点击删除按钮，出现确认框  状态截图保存为
	   3.
	*/
	
	//请求成功后切换到子框集 
	casper.withFrame('pageMain',function(){
		  test.assertTitle('视频管理','switch into frame successfully');
		  
		  //点击进行视频编辑
		  casper.thenEvaluate(function() {
//			  var editBtn=document.querySelector('.video-edit-link');
//			 // var editBtn=document.getElementsByClassName('video-edit-link');
//			  editBtn.click();
			  document.getElementById('videoSearch').value = 'userddname';
//			  document.getElementById('test').click(); 
//			  $('.video-edit-link')[0].trigger('click');
//			  console.log(editBtn);
//			  this.echo('ddd');
		  },'sheldon.cooper', 'b4z1ng4');
		  
//		  casper.then(function() {
//			  var editBtn=document.getElementsByClass('video-edit-link');
//			  console.log(editBtn);
//		  });
		  
		  //
		  casper.wait(500, function() {
			//  this.captureSelector('../../img/GUItest/now.png','html');
		  });
		  
		  //点击进入上传模式
		  casper.then(function() {
			  this.click('a[id="uploadModel"]');
		  });
		  
		  //点击500ms后确认到达上传模式且填写表单信息
		  casper.wait(500, function() {
			  test.assertTitle('视频上传','jump to upload successfully');
			  this.fill('form[id="uploadForm"]', {
					'videoName': 'admin',
					'videoIntro': 'admin123456'
			  },false);
			  this.fillSelectors("form[id='uploadForm']",{"select[name='videoType']":"1"});
			  this.fillSelectors("form[id='uploadForm']",{"select[name='videoClass']":"3"});
			  this.page.uploadFile("input[name='videoImg']",'C:/videoImg.jpg');
			  this.page.uploadFile("input[name='videoAddress']",'C:/videoAddress.mp4');
		  });
		  
		 //500ms后切换到父级截图
		 casper.wait(500, function() {
			 var img=this.getElementInfo('#upImgFile');
			 this.page.switchToParentFrame();
			 this.captureSelector('../../img/GUItest/uploadVideo.png','html');
		 });
 
		 //上传操作完成后回退至首页
		 casper.then(function() {
			 casper.back(); 
			 
		 });
		 
		 //点击进入批量删除模式 
		  casper.wait(500, function() {
			  this.page.switchToChildFrame(0);
			// this.captureSelector('../../img/GUItest/now.png','html');
			  test.assertTitle('视频管理','back to video successfully'); 
			  this.click('a[id="delModel"]');
			  this.echo('click into delModel');
		  });
		  
		  //在未勾选任何视频的情况下点击删除按钮
		  casper.then(function(){
			  this.click('a[id="delVideo"]');
			  this.echo("click delVideo without checked any video");
		  });
		  
		 //点击500ms后切换到父级截图
		 casper.wait(500,function(){
			 this.page.switchToParentFrame();
			 this.captureSelector('../../img/GUItest/deleteNoVideo.png','html');
			 //切换到子frame点击关闭警告弹框
			 this.page.switchToChildFrame(0);
			 this.click('button[id="noVideoBtn"]');
		 });
		 
		 casper.wait(500,function(){
			 var videoItem = this.getElementsInfo('.list-box-item');
			 console.log(videoItem[4]);
			// this.click('');
			 this.captureSelector('../../img/GUItest/deleteVideo.png', 'html');
		 });
		 
	});
	
	//运行
	casper.run(function(){
		test.done();
	});
});

casper.test.begin('login in vr in a wrong account', 2, function(test){
	//进入登陆页
	casper.viewport(1366,900).start('http://localhost:8080/yuexinvr/showLogin');
	
	//登陆等待500ms进行登陆信息填充，填好后不自动提交
	casper.wait(500, function() {
		if (this.exists('div[id="loginBox"]')) {
			this.fill('div[id="loginBox"]', {
				'userName': 'admin',
				'password': 'admin12345'
			},false);
		}
	});
	
	//填好后点击提交按钮进行提交
	casper.then(function() {
		if (this.exists('input[class="login-btn"]')) {
			this.click('input[class="login-btn"]');
			this.echo('login...');
		}
	});
	
	//提交后等待500ms再进行判断
	casper.wait(500, function() {
		test.assertUrlMatch('http://localhost:8080/yuexinvr/showLogin','stay in login page');
		test.assertVisible('.login-warn','have the warning message');
		this.captureSelector('../../img/GUItest/login-failure.png', '#loginManage');
	});
	
	//运行
	casper.run(function(){
		test.done();
	});
});