var expect=chai.expect;
var isWrong;
var lurl = "login";
var type = "post";
/* *
 * 可用账户：
 * userName:admin
 * password:admin123456
*/
describe('Login', function(){
	describe('#Submit', function(){
		
		it('期望当账号和密码都正确时不显示错误提示信息--',function(done){
			var loginData = {
				"userName":"admin",
				"password": "admin123456"
			};
			$.ajaxs(lurl, type, loginData, function (data){
				if (data.errorCode == 20001){//用户名密码错误显示错误提示信息
					isWrong = true;
				}else if (data.errorCode == 10000){//正确则直接跳转至主页
					isWrong = false;
					//window.location.href = "index";
				}
				expect(isWrong).to.equal(false);
				done();
			},function (){
				alert("服务器错误");
				done();
			})
		})
		
		it('期望当账号错误时显示错误提示信息--',function(done){
			var loginData = {
				"userName":"admi",
				"password": "admin123456"
			};
			$.ajaxs(lurl, type, loginData, function (data){
				if (data.errorCode == 20001){//用户名密码错误显示错误提示信息
					isWrong = true;
				}else if (data.errorCode == 10000){//正确则直接跳转至主页
					isWrong = false;
					//window.location.href = "index";
				}
				expect(isWrong).to.equal(true);
				done();
			},function (){
				alert("服务器错误");
				done();
			})
		})
		
		it('期望当密码错误时显示错误提示信息--',function(done){
			var loginData = {
				"userName":"admin",
				"password": "admin1234"
			};
			$.ajaxs(lurl, type, loginData, function (data){
				if (data.errorCode == 20001){//用户名密码错误显示错误提示信息
					isWrong = true;
				}else if (data.errorCode == 10000){//正确则直接跳转至主页
					isWrong = false;
					//window.location.href = "index";
				}
				expect(isWrong).to.equal(true);
				done();
			},function (){
				alert("服务器错误");
				done();
			})
		})
		
	})
})