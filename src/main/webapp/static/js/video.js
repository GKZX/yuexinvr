var video=new Vue ({
	el: "#videoManager",
	data: {
		fid: "",
		id: "",
		sortType: 2,
		pageSize: 10,
		searchCriteria: "",
		vedioIds: "",
		message: "",
		sclass: [],
		isEdit: false
	},
	methods: {
		//删除模式
		editVideo:function() {
		    this.isEdit = true;
		},
		//视频列表模式
		backVideo:function() {
			this.isEdit = false;
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
				$('#choiseModal').modal('show');
			}else{
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
		        	for(var i=0; i<lists.length; i++){
						if(lists[i].checked == true){
							lists.splice(i,1);
							i--;
						}
					}
		        	video.isEdit = false;  	
		        } else if(data.errorCode == 2002){
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
	var id = getParam("id");
	video.fid = id;
	video.id = id;
	loadData( id, video.sortType, 1, video.pageSize);	
	classLoad(id);
})

//加载视频数据
function loadData( id, type, page, len, search){
	search = search?search:"";
	var lurl = "/vedio/getVedios";
	var ltype = "get";
	var videoData= {
		"vedioCategoryId": id,
		"searchCriteria": search,
		"indexPage": page,
		"pageSize": len,
		"sortType": type
	};
	$.ajaxs(lurl, ltype, videoData, function(data){
		console.log(data);
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
        }else if(data.errorCode == 2002){
        	top.location.href = "showLogin";
        }
	},function(){
		alert("服务器错误");
	})
}

//加载类
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
        } else if(data.errorCode == 2002){
        	top.location.href = "showLogin";
        }
	},function (){
		alert("服务器错误");
	})
}
