var upload=new Vue({
	el:"#uploadManage",
	data:{
		fclass:[],
		sclass:[],
		isfee:false,
		info:{
			type:0,
			vedioId:"",
			vedioName:"",
			vedioNotes:"",
			vedioImgUrl:"",
			vedioUrl:"",
			isFree:0,
			money:"",
			vedioCategoryPId:"",
			vedioCategoryId:""
		}
	},
	methods:{
		//父级分类改变触发子类改变
		choise:function(e){
			var val=e.currentTarget.value;
			classLoad(val);
		},
		//点击提交
		upload:function(){
		    this.info.vedioImgUrl="dd.jpg";
		    this.info.vedioUrl="movie.mp4";
		    if(this.sclass.length==0){
		    	this.info.vedioCategoryId="";
		    }
		    var uurl="/vedio/addOrEditVedio";
		    var utype="post";
		    var upData=this.info;
		   // console.log(upData);
		    $.ajaxs(uurl,utype,upData,function(data){
		        if(data.errorCode==10000){//成功
		        	window.location.href="video";//有问题
		        }
			},function(){
				alert("wrong");
			})
			
		}
	}
})

$(function(){
	$(".uploadFile").eq(0).change(function(){
	   var path=$(".uploadFile").val();
	   var extStart=path.lastIndexOf('.');
	   var ext=path.substring(extStart,path.length).toUpperCase();
	   if(ext!='.BMP'&&ext!='.PNG'&&ext!='.GIF'&&ext!='.JPG'&&ext!='.JPEG'){
		  alert('图片限于png,gif,jpeg,jpg格式');
	   }else{
	   	    var file=this.files[0];
			var reader=new FileReader();
			reader.onload=function(e){
				var url=e.target.result;	
				$(".upload-cover-preview").html('<img src="'+url+'" class="imgpreview-image">');
			}
			reader.readAsDataURL(file);	
		}
	})
	$(".upVideoFile").eq(0).change(function(){
		
	})
	$("#updataBtn").click(function(){
	   $(".uploadFile").trigger("click");
	});
	$("#upVideoBtn").click(function(){
		$(".upVideoFile").trigger("click");
	})
	classLoad(0);
	classLoad(1);	
	//编辑页获得传过来的id
	var id=getParam("id");
	var type=getParam("type");
	if(type==1){//为编辑状态
		EditData(id);
	}
	upload.info.vedioId=id;
	upload.info.type=type;
	
})

//加载视频分类
function classLoad(Id){
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":Id
	};
	$.ajaxs(curl,ctype,classData,function(data){
        if(data.errorCode==10000){//成功
        	if(Id==0){
        		upload.fclass=data.vedioCategoryList;
        		console.log(upload.fclass[0].id);
        		upload.info.vedioCategoryPId=upload.fclass[0].id;
        	}else{
        		upload.sclass=data.vedioCategoryList;
        		console.log(upload.sclass);
        		if(upload.sclass.length>0){
        			upload.info.vedioCategoryId=upload.sclass[0].id;
        		}else{
        			upload.info.vedioCategoryId="";
        		}
        	}
        	
        }
	},function(){
		alert("wrong");
	})
}

//编辑加载数据
function EditData(Id){
	var vurl="/vedio/getVedio";
	var vtype="get";
	var vedioData={
		"vedioId":Id
	};
	$.ajaxs(vurl,vtype,vedioData,function(data){
		console.log(data);
        if(data.errorCode==10000){//成功
        	var vedio=data.vedio;
        	var info=upload.info;
        	info.vedioName=vedio.vedioName;
            info.vedioNotes=vedio.vedioNotes;
            info.isFree=vedio.isFree;
            info.money=vedio.money;
            info.vedioCategoryId=vedio.vedioCategoryId;
            info.vedioCategoryPId=vedio.vedioCategoryPId;
        }
	},function(){
		alert("wrong");
	})
} 

function check(){
	//alert(2);
	//return true;
	
}