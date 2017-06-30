var video=new Vue ({
	el: "#videoManager",
	data: {
		fid: "",             //视频大类ID
		id: "",              //视频小类ID
		sortType: 2,         //排序方式   默认按最新上传排序
		pageSize: 10,        //每页加载的视频数量
		searchCriteria: "",  //搜索条件
		vedioIds: "",        //要删除的视频ID '1,2,3'形式
		message: "",         //视频列表数据  包括vedioList vedioSize
		sclass: [],          //视频分类
		isDel: false         //批量删除模式  默认为否
	},
	methods: {
		//删除模式
		delModel:function() {
		    this.isDel= true;
		},
		//视频列表模式
		backVideo:function() {
			this.isDel = false;
		},
		//勾选视频
		checkVideo:function(index){
			this.message.vedioList[index].checked = !this.message.vedioList[index].checked;
		},
		//点击删除视频
		delVideo:function(){
			var lists = this.message.vedioList;
			var vedioArr = [];
			for(var i=0; i<lists.length; i++){
				if (lists[i].checked == true){
					vedioArr.push(lists[i].id);
				}
			}
			var vedioIds = vedioArr.join();
			this.vedioIds = vedioIds;
			if(vedioIds == null||vedioIds == ""||vedioIds == " "){
				//未选择视频就点击删除提示选择视频
				$('#choiseModal').modal('show');
			}else{//选择了视频弹出确认删除的确认框
				$('#delModal').modal('show');
			}	
		},
		//确定删除
		confirmDel:function(){
			var lists=this.message.vedioList;
			var durl="/vedio/deleteVedios";
			var dtype="POST";
			var ddata={
					"vedioIds": this.vedioIds
			}
			$.ajaxs(durl, dtype, ddata, function (data){
		        if(data.errorCode == 10000){//成功
					//删除视频请求执行成功则在页面视图中删除相应视频
		        	for(var i=0; i<lists.length; i++){
						if(lists[i].checked == true){
							lists.splice(i,1);
							i--;
						}
					}
		        	video.isDel = false;  	//删除成功退出编辑模式
		        } else if(data.errorCode == 2002){//未登录
		        	top.location.href = "showLogin";
		        }
			},function (){
				alert("服务器错误");
			})
		},
		//排序方式
		sort:function (type){
			this.sortType = type;
			loadData(this.id, type, 1, this.pageSize);	
		},
		//分类显示
		choiseClass:function (e){
			var id = e.currentTarget.id;
		    this.id = e.currentTarget.id;
			var type = this.sortType;
			loadData(id, type, 1, this.pageSize);
		},
		//搜索视频
		search:function (){
			var search = this.searchCriteria;
			var id = this.id;
			var type = this.sortType;
			loadData(id, type, 1, this.pageSize, search)
		},
		//点击类型活跃状态切换
		active:function(e){
			var activeItem = e.currentTarget.parentNode;
			$(activeItem).addClass("active").siblings().removeClass("active");  
		}
	}
})

$(function(){
	var id = getParam("id");//从侧边栏点击传过来的视频大类ID
	video.fid = id;//视频大类ID
	video.id = id;//视频小类ID默认选中全部视频
	loadData( id, video.sortType, 1, video.pageSize);	
	classLoad(id);
})

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
        if(data.errorCode == 10000){//成功
        	//向message数据结构中给每一项添加勾选属性
        	for (var i=0;i<data.vedioList.length;i++){
        		data.vedioList[i].checked = "";
		    }
           video.message = data;
           //加载分页
           var size = data.vedioSize;
           $(".M-box2").html(" ");
	       if (size > len){  
	           $('.M-box2').pagination({
	               coping: true,
                   homePage: '首页',
                   endPage: '末页',
                   totalData: size,
                   showData: len,
                   current: page,
                   callback: function(api) {
                       var now=api.getCurrent();
                       loadData(video.id, video.sortType, now, video.pageSize);
                   }
	           });
	       }
        }else if(data.errorCode == 20002){
        	top.location.href = "showLogin";
        }
	},function(){
		alert("服务器错误");
	})
}

//加载分类
function classLoad(Id){
	var curl = "vedio/getVedioCateGory";
	var ctype = "get";
	var classData = {
		"vedioCategoryPId": Id
	};
	$.ajaxs(curl, ctype, classData, function(data){
        if(data.errorCode == 10000){//成功
        	var classList = data.vedioCategoryList;
        	video.sclass = classList;
        } else if(data.errorCode == 20002){//未登录
        	top.location.href = "showLogin";
        }
	},function (){
		alert("服务器错误");
	})
}
