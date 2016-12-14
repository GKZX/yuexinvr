var video=new Vue({
	el:"#videoManager",
	data:{
		fid:"",
		id:"",
		sortType:2,
		message:"",
		sclass:[],
		isEdit:false
	},
	methods:{
		editVideo:function(){
		    this.isEdit=true;
		},
		backVideo:function(){
			this.isEdit=false;
		},
		checkVideo:function(index){
			this.message.vedioList[index].checked=!this.message.vedioList[index].checked;
		},
		delVideo:function(){
			var lists=this.message.vedioList;
			var vedioIds=[];
			for(var i=0;i<lists.length;i++){
				if(lists[i].checked==true){
					vedioIds.push(parseInt(lists[i].id));
				}
			}
			alert(vedioIds);
			var durl="/vedio/deleteVedios";
			var dtype="POST";
			var ddata={
					"vedioIds":vedioIds
			}
			console.log(ddata);
			$.ajaxs(durl,dtype,ddata,function(data){
				console.log(data);
		        if(data.errorCode==10000){//成功
		        	for(var i=0;i<lists.length;i++){
						if(lists[i].checked==true){
							lists.splice(i,1);
							i--;
						}
					}
		        }
			},function(){
				alert("wrong");
			})
		},
		sort:function(type){
			this.sortType=type;
			loadData(this.id,type);
		},
		choiseClass:function(e){
			var id=e.currentTarget.id;
		    this.id=e.currentTarget.id;
			var type=this.sortType;
			loadData(id,type);
		}
	}
})
//时间戳转日期格式过滤器
Vue.filter("formatDate",function(value){
	var now=new Date(value);
	var year=now.getFullYear();     
    var month=now.getMonth()+1;     
    var date=now.getDate();        
    return year+"-"+month+"-"+date;  
})
//类型过滤器
Vue.filter("formatType",function(value){
	return type=value==0?"免费":"收费";
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
//加载数据
function loadData(id,type){
	var lurl="/vedio/getVedios";
	var ltype="get";
	var videoData={
		"vedioCategoryId":id,
		"searchCriteria":"",
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
