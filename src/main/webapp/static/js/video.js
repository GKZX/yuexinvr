var video=new Vue({
	el:"#videoManager",
	data:{
		fid:"",
		id:"",
		sortType:2,
		searchCriteria:"",
		message:"",
		sclass:[],
		isEdit:false
	},
	methods:{
		//删除模式
		editVideo:function(){
		    this.isEdit=true;
		},
		//视频列表模式
		backVideo:function(){
			this.isEdit=false;
		},
		//勾选视频
		checkVideo:function(index){
			this.message.vedioList[index].checked=!this.message.vedioList[index].checked;
		},
		//删除勾选视频
		delVideo:function(){
			var lists=this.message.vedioList;
			var vedioArr=[];
			for(var i=0;i<lists.length;i++){
				if(lists[i].checked==true){
					vedioArr.push(lists[i].id);
				}
			}
			var vedioIds=vedioArr.join();
			if(vedioIds==null||vedioIds==""||vedioIds==" "){
				alert("请选择要删除的视频");
			}else{
				var durl="/vedio/deleteVedios";
				var dtype="POST";
				var ddata={
						"vedioIds":vedioIds
				}
				$.ajaxs(durl,dtype,ddata,function(data){
					console.log(data);
			        if(data.errorCode==10000){//成功
			        	for(var i=0;i<lists.length;i++){
							if(lists[i].checked==true){
								lists.splice(i,1);
								i--;
							}
						}
			        	video.isEdit=true;
			        }
				},function(){
					alert("wrong");
				})
			}
			
		},
		//排序方式
		sort:function(type){
			this.sortType=type;
			loadData(this.id,type);
		},
		//分类显示
		choiseClass:function(e){
			var id=e.currentTarget.id;
		    this.id=e.currentTarget.id;
			var type=this.sortType;
			loadData(id,type);
		},
		//搜索视频
		search:function(){
			var search=this.searchCriteria;
			var id=this.id;
			var type=this.sortType;
			//alert(id);
			loadData(id,type,search)
		}
	}
})
$(function(){
	var id= getParam("id");
	video.fid=id;
	video.id=id;
	loadData(id,video.sortType);	
	classLoad(id);
	//点击类型活跃状态切换
	$(document).on("click",".class-item",function(){
		$(".class-item").removeClass("active");
		$(this).addClass("active");
	});
	$(document).on("click",".sort-item",function(){
		$(".sort-item").removeClass("active");
		$(this).addClass("active");
	})
})
//加载视频数据
function loadData(id,type,search){
	search=search?search:"";
	var lurl="/vedio/getVedios";
	var ltype="get";
	var videoData={
		"vedioCategoryId":id,
		"searchCriteria":search,
		"indexPage":1,
		"pageSize":20,
		"sortType":type
	};
	$.ajaxs(lurl,ltype,videoData,function(data){
		console.log(data);
        if(data.errorCode==10000){//成功
        	for(var i=0;i<data.vedioList.length;i++){
        		data.vedioList[i].checked="";
		    }
           video.message=data;
           
        }
	},function(){
		alert("wrong");
	})
}
//加载类
function classLoad(Id){
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":Id
	};
	$.ajaxs(curl,ctype,classData,function(data){
        if(data.errorCode==10000){//成功
        	var classList=data.vedioCategoryList;
        	video.sclass=classList;
        	console.log(video.sclass);
        }
	},function(){
		alert("wrong");
	})
}
