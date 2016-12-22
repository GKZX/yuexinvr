var expect=chai.expect;
describe('User', function(){
	describe('#userLoad()', function(){
		it('期望返回状态码为10000',function(done){
			userLoad(1,20);
			
		});
		it('期望用户数据列表首次加载第一页不超过20条数据',function(done){
			var userManager=new Vue({
				el:"#userManager",
				data:{
					userList:""
				}
			});
			userLoad(1,20,function(data,page,len){
		        if(data.errorCode==10000){//成功
		        	//渲染用户数据列表
		         	userManager.userList=data.userList;
		         	expect(userManager.userList).to.equal(data.userList);
		         	expect(userManager.userList).to.have.length.most(20);
		         	expect(userManager.userList).to.be.an('Array');
		         	done();
		        }
			},function(){
				alert("服务器错误");
			});
			
		});
		it('',function(done){
			userLoad(1,20,function(data,page,len){
				//当用户数量超过一页的数量时显示分页
	        	var size=data.userSize;
	        	if(size>len){
	        		 $('.M-box2').pagination({
	                     coping:true,
	                     homePage:'首页',
	                     endPage:'末页',
	                     totalData:size,
	                     showData:len,
	                     current:page,
	                     callback:function(api){
	                           var now=api.getCurrent();
	                           userLoad(now,len);
	                           expect()
	                           done();
	                      }
	                 });
	        	}
			})
		})
	})
})