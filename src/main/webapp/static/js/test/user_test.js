var expect=chai.expect;
describe('User', function(){
	describe('#userLoad()', function(){
		it('期望返回状态码为10000',function(done){
			userLoad(1,20,function(data,page,len){
				expect(data.errorCode).to.equal(10000);
				done();
			});
			
		});
		it('期望用户数据列表加载第一页20条数据',function(done){
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
		         	expect(userManager.userList).to.have.length(20);
		         	done();
		        }
			},function(){
				alert("服务器错误");
			});
			
		})
	})
})