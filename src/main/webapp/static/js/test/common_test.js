var expect=chai.expect;
describe('Common', function(){
	/**
	 * 测试数据：获取用户数据接口
	*/
	describe('#$.ajaxs()', function(){
		it('期望$.ajaxs方法执行成功--',function(done){
			var uurl="/user/getUsers";
			var utype="get";
			var userData={
				"indexPage":1,
				"pageSize":20
			};
			$.ajaxs(uurl,utype,userData,function(data){
				console.log('期望$.ajaxs方法执行成功--');
	        	console.log(data);
	        	expect(data.errorCode).to.equal(10000);
		        done();
			},function(){
				console.log("失败");
				done();
			})
		})
	})
	/**
	 * 测试数据：url="?id=3"
	*/
	describe('#getParam()',function(){
		it('期望获得地址栏问号之后指定的参数值--',function(){
			function getParam(name){  //获取参数
				var url="?id=3";  //获取问号之后的字符
				var reg=new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
				if(url!=null && url.toString().length>1)
				{ 
				var r=url.substr(1).match(reg);
				if(r!=null)return unescape(r[2]); return null;
				}
			};
			var id=getParam("id");
			expect(id).to.equal('3');
		})	
	})
	/**
	 * 测试数据：10~30
	*/
	describe('#GetRandomNum()',function () {
		it('期望获取10~30的随机数--',function () {
			var num=GetRandomNum(10,30);
			console.log('期望获取10~30的随机数--' + num);
			expect(num).to.within(10,30);
		})	
	})
	
	/**
	 * 测试数据：n=7
	*/
	describe('#generateMixed(n)',function () {
		it('期望获取7位随机字符串--',function (done) {
			var string=generateMixed(7);
			console.log("期望获取7位随机字符串--"+string);
			expect(string).to.have.length(7);
			expect(string).to.be.a("string");
			done();
		})	
	})
	
	
})