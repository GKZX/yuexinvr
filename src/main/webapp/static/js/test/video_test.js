var expect=chai.expect;
describe('Video', function(){
	/**
	 * 测试接口：视频分类
	*/
	describe('#classLoad()', function(){
		it('期望加载出视频大类不为空--',function(done){
			function classLoad(Id){
				var curl = "vedio/getVedioCateGory";
				var ctype = "get";
				var classData = {
					"vedioCategoryPId": Id
				};
				$.ajaxs(curl, ctype, classData, function(data){
				    console.log('期望加载出视频大类不为空--' + data.vedioCategoryList);
				    expect(data.errorCode).to.equal(10000);
				    expect(data.vedioCategoryList).to.be.a("Array");
				    expect(data.vedioCategoryList).to.have.length.above(0);
			        done();
				},function (){
					alert("服务器错误");
					done();
				})
			}
			//当ID传为0或者空时加载视频大类
			classLoad(0);
		})
	})
	/**
	 * 测试接口：视频信息
	 * 测试数据：
	 *    分类ID(id)：全部分类 0 各子类ID 
	 *    排序类型(type)：0-全部;1-播放量;2-最新上传;3-销售量;
	 *    分页(page)：0,1,2...
	 *    每页条数(len)： 例：10
	 *    搜索关键字(search)： 例：'过山车'
	*/
	describe('#loadData()', function(){
		it('期望获得以播放量排序第一个大分类下的第一页的不超过5条数据--',function(done){
			//加载视频数据
			function loadData( id, type, page, len, search){
				search = search?search:"";
				var lurl = "/vedio/getVedios";
				var ltype = "get";
				var videoData= {
					"vedioCategoryId": id,    //分类ID
					"searchCriteria": search, //搜索条件
			  		"indexPage": page,        //第几页
					"pageSize": len,          //每页的视频数量
					"sortType": type          //排序类型
				};
				$.ajaxs(lurl, ltype, videoData, function(data){
					console.log('期望获得以播放量排序全部分类下的第一页的不超过5条数据--');
					console.log(data);
					expect(data.errorCode).to.equal(10000);
					expect(data.vedioList).to.have.length.most(5);
					var list=data.vedioList;
					//以第一个视频和第二个视频的播放量大小来判断是否是按照播放量降序排列
					var order=list[0].playAmount >= list[1].playAmount?true:false;
					expect(order).to.equal(true);
					expect(list[0].vedioCategoryId).to.equal(1);
					for (var i=0;i<data.vedioList.length;i++){
		        		data.vedioList[i].checked = "";
		        		expect(list[i]).to.have.property('checked');
				    }
					done();
				},function(){
					alert("服务器错误");
					done();
				})
			}
			loadData(1,1,1,5,"");
		})
	})
})