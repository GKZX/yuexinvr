var expect=chai.expect;
describe('User', function(){
	describe('#userLoad()', function(){
		
		it('期望请求用户数据列表ajax成功--',function(done){
			function userLoad(page , len){
				var uurl = "/user/getUsers";
				var utype = "get";
				var userData = {
					"indexPage": page,
					"pageSize": len
				};
				$.ajaxs(uurl, utype, userData, function(data){
					expect(data.errorCode).to.equal(10000);
					done();
				},function(){
					alert('服务器错误');
					done();
				})
			}	
			userLoad(1,30);
		});
		
		it('期望每页加载的数据不超过限定值--',function(done){
			function userLoad(page , len){
				var uurl = "/user/getUsers";
				var utype = "get";
				var userData = {
					"indexPage": page,
					"pageSize": len
				};
				$.ajaxs(uurl, utype, userData, function(data){
					 if (data.errorCode == 10000){//成功
			             //渲染用户数据列表
			         	 userManager.userList = data.userList;
			         	 console.log('期望每页加载的数据不超过限定值30--');
			         	 console.log(userManager.userList.length);
			         	 expect(userManager.userList.length).to.most(len);
			         	 done();
					 }
				},function(){
					alert('服务器错误');
					done();
				})
			}	
			userLoad(1,30);		
		});
		
		it('期望用户数量超过一页的数量时显示分页--',function(done){
			function userLoad(page , len){
				var uurl = "/user/getUsers";
				var utype = "get";
				var userData = {
					"indexPage": page,
					"pageSize": len
				};
				$.ajaxs(uurl, utype, userData, function(data){
					 if (data.errorCode == 10000){//成功
						//模拟用户数量为40个
			         	 userManager.userSize = 40;
			         	 var show;
			        	 if (userManager.userSize > len){
			        		show = true;
			        	 } else{
			        		show = false;
			        	 }
			        	 expect(data.userSize).to.be.a("number");
			        	 expect(show).to.equal(true);
				         done();
					 }
				},function(){
					alert('服务器错误');
					done();
				})
			}	
			userLoad(1,30);		
		});
		
		it('期望用户数量未超过一页的数量时不显示分页--',function(done){
			function userLoad(page , len){
				var uurl = "/user/getUsers";
				var utype = "get";
				var userData = {
					"indexPage": page,
					"pageSize": len
				};
				$.ajaxs(uurl, utype, userData, function(data){
					 if (data.errorCode == 10000){//成功 
						//模拟用户数量为40个
			         	 userManager.userSize = 20;
			         	 var show;//分页的是否显示
			        	 if (userManager.userSize > len){
			        		show = true;
			        	 } else{
			        		show = false;
			        	 }
			        	 expect(data.userSize).to.be.a("number");
			        	 expect(show).to.equal(false);
				         done();
					 }
				},function(){
					alert('服务器错误');
					done();
				})
			}	
			userLoad(1,30);		
		});
		
		it('期望请求得到的用户列表为数组格式--',function(done){
			function userLoad(page , len){
				var uurl = "/user/getUsers";
				var utype = "get";
				var userData = {
					"indexPage": page,
					"pageSize": len
				};
				$.ajaxs(uurl, utype, userData, function(data){
					 if (data.errorCode == 10000){//成功
						//渲染用户数据列表
				        userManager.userList = data.userList;
						console.log("用户数据列表：" + userManager.userList);
						expect(userManager.userList).to.be.a("array");
				        done();
					 }
				},function(){
					alert('服务器错误');
					done();
				})
			}	
			userLoad(1,30);		
		});
		
	})
})