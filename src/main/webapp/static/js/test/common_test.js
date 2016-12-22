var expect=chai.expect;
describe('Common', function(){
	describe('#$.ajaxs()', function(){
		it('期望$.ajaxs方法执行状态码为10000',function(done){
			var uurl="/user/getUsers";
			var utype="get";
			var userData={
				"indexPage":1,
				"pageSize":20
			};
			$.ajaxs(uurl,utype,userData,function(data){
	        	console.log(data);
	        	expect(data.errorCode).to.equal(10000);
	        	
		        done();
			},function(){
				console.log("失败");
				done();
			})
		})
	})
	
})