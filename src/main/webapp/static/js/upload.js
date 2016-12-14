var upload=new Vue({
	el:"#uploadManage",
	data:{
		fclass:[],
		sclass:[],
		isfee:false,
		selected:1,
		sselected:3,
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
			vedioCategoryId:3
		}
	},
	methods:{
		choise:function(){
			
		},
		upload:function(){
		    this.info.vedioImgUrl="dd.jpg";
		    this.info.vedioUrl="movie.mp4";
		    this.info.vedioCategoryPId=this.selected;
		    if(this.sclass.length==0){
		    	this.info.vedioCategoryId="";
		    }else{
		    	this.info.vedioCategoryId=this.sselected;
		    }
		    var uurl="/vedio/addOrEditVedio";
		    var utype="post";
		    var upData=this.info;
		    console.log(upData);
		    $.ajaxs(uurl,utype,upData,function(data){
		        if(data.errorCode==10000){//成功
		        	window.location.href="video";//有问题
		        }
			},function(){
				alert("wrong");
			})
			
		}
	},
	watch:{
		selected:function(val){
			classLoad(val);
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
	upload.info.vedioId=id;
	upload.info.type=type
	alert(upload.info.vedioId);
	alert(upload.info.type);
})

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
        	}else{
        		upload.sclass=data.vedioCategoryList;
        	}
        	
        }
	},function(){
		alert("wrong");
	})
}

function check(){
	//alert(2);
	//return true;
	
}