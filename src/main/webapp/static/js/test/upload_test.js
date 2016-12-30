var expect=chai.expect;
var vedioInfo=upload.info;
vedioInfo.type=0;
vedioInfo.vedioCategoryPId=2;
vedioInfo.isFree=0;
vedioInfo.vedioName="视频名称";
vedioInfo.vedioNotes="视频简介";
vedioInfo.vedioImgUrl="img/album.png";
vedioInfo.vedioUrl="video.mp4";
describe('Upload', function(){
	/**
	 * 测试数据：
	*/
	describe('#check()', function(){
		it('期望当数据合法时检测通过--',function(){
			var result = check();
			expect(result).to.equal(true);
		});
		it('期望当父类ID为空时检测不通过--',function(){
			upload.info.vedioCategoryPId="";
			var result = check();
			expect(result).to.equal(false);
			upload.info.vedioCategoryPId=2;
		})
		it('期望当视频名称为空时检测不通过--',function(){
			upload.info.vedioName="";
			var result = check();
			expect(result).to.equal(false);
			upload.info.vedioName="视频名称";
		})
		it('期望当视频简介为空时检测不通过--',function(){
			upload.info.vedioNotes="";
			var result = check();
			expect(result).to.equal(false);
			upload.info.vedioNotes="视频简介";
		})
		it('期望当视频封面地址为空时检测不通过--',function(){
			upload.info.vedioImgUrl="";
			var result = check();
			expect(result).to.equal(false);
			vedioInfo.vedioImgUrl="img/album.png";
		})
		it('期望当视频地址为空时检测不通过--',function(){
			upload.info.vedioUrl="";
			var result = check();
			expect(result).to.equal(false);
			vedioInfo.vedioUrl="video.mp4";
		})
		it('期望当收费时费用金额为空时检测不通过--',function(){
			vedioInfo.isFree=1;
			vedioInfo.money="";
			var result = check();
			expect(result).to.equal(false);
		})
		it('期望当收费时费用金额包含特殊字符时检测不通过--',function(){
			vedioInfo.isFree=1;
			vedioInfo.money="8dd";
			var result = check();
			expect(result).to.equal(false);
		})
		it('期望当收费时费用金额为0时检测不通过--',function(){
			vedioInfo.isFree=1;
			vedioInfo.money="0";
			var result = check();
			expect(result).to.equal(false);
		})
		it('期望当收费时费用金额大于0时检测通过--',function(){
			vedioInfo.isFree=1;
			vedioInfo.money="2";
			var result = check();
			expect(result).to.equal(true);
		})
	})
	/**
	 * 测试接口：加载分类
	 * 测试数据：
	 *    所属的父分类ID(Id)：0 全部父类  其余 加载对应父类ID的子类
	 *    所属的子分类ID(sId)：
	*/
	describe('#classLoad(Id, sId)', function(){
		it('期望当只传父ID为0时获取所有的父分类元素--',function(done){
			//加载视频分类
			function classLoad(Id, sId){//父类ID  子类ID
				expect(sId).to.be.an("undefined");
				var curl = "vedio/getVedioCateGory";
				var ctype = "get";
				var classData = {
					"vedioCategoryPId": Id
				};
				$.ajaxs(curl, ctype, classData, function(data){
					expect(data.errorCode).to.equal(10000);
					console.log('期望当只传父ID为0时获取所有的父分类元素--');
					console.log(data.vedioCategoryList);
					for(var i=0;i<data.vedioCategoryList.length;i++){
						expect(data.vedioCategoryList[i].pId).to.equal(0);
					}
			        done();
				},function (){
					alert("服务器错误");
					done();
				})
			}
			classLoad(0);
		})
		it('期望当只传父ID为3时获取父元素为3对应的所有子元素--',function(done){
			//加载视频分类
			function classLoad(Id, sId){//父类ID  子类ID
				expect(sId).to.be.an("undefined");
				var curl = "vedio/getVedioCateGory";
				var ctype = "get";
				var classData = {
					"vedioCategoryPId": Id
				};
				$.ajaxs(curl, ctype, classData, function(data){
					expect(data.errorCode).to.equal(10000);
					console.log('期望当只传父ID为3时获取父元素为3对应的所有子元素--');
					console.log(data.vedioCategoryList);
					for(var i=0;i<data.vedioCategoryList.length;i++){
						expect(data.vedioCategoryList[i].pId).to.equal(3);
					}
					if (upload.sclass.length > 0){//有子类则默认选中其子类的第一个
						upload.info.vedioCategoryId = upload.sclass[0].id;
						expect(upload.info.vedioCategoryId).to.equal(upload.sclass[0].id);
            		}else {//没有子类则子类下拉列表置为空
            			upload.info.vedioCategoryId = "";
            			expect(upload.info.vedioCategoryId).to.equal("");
            		}
			        done();
				},function (){
					alert("服务器错误");
					done();
				})
			}
			classLoad(3);
		})
		it('期望当传父ID为3且子ID为20时处于编辑状态选中传进来的ID值--',function(done){
			//加载视频分类
			function classLoad(Id, sId){//父类ID  子类ID
				expect(sId).to.not.be.an("undefined");
				var curl = "vedio/getVedioCateGory";
				var ctype = "get";
				var classData = {
					"vedioCategoryPId": Id
				};
				$.ajaxs(curl, ctype, classData, function(data){
					expect(data.errorCode).to.equal(10000);
					console.log('期望当传父ID为3且子ID为20时处于编辑状态选中传进来的ID值--');
					console.log(data.vedioCategoryList);
					for(var i=0;i<data.vedioCategoryList.length;i++){
						expect(data.vedioCategoryList[i].pId).to.equal(3);
					}
					upload.info.vedioCategoryId = sId;//子类ID
        			upload.info.vedioCategoryPId = Id;//父类ID
        			expect(upload.info.vedioCategoryId).to.equal(sId);
        			expect(upload.info.vedioCategoryPId).to.equal(Id);
			        done();
				},function (){
					alert("服务器错误");
					done();
				})
			}
			classLoad(3,20);
		})
	})
})