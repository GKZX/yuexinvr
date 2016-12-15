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
	//classLoad(0);//写在这里会导致随机父元素选择第一个还是已经选择的那个
	//编辑页获得传过来的id
	var id=getParam("id");
	var type=getParam("type");
	if(type==1){//为编辑状态
		EditData(id);	
	}else{//为上传状态
		classLoad(0);
		classLoad(1);	
	}
	upload.info.vedioId=id;
	upload.info.type=type;
	
})

//加载视频分类
function classLoad(Id,sId){
	console.log(typeof(sId)=="undefined");
	var curl="vedio/getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":Id
	};
	$.ajaxs(curl,ctype,classData,function(data){
        if(data.errorCode==10000){//成功
        	if(Id==0){//父分类元素的渲染
        		upload.fclass=data.vedioCategoryList;
        		if(typeof(sId)=="undefined"){//上传时选择默认的第一个值
        			console.log("上传");
        			upload.info.vedioCategoryPId=upload.fclass[0].id;
        		}else{
        			console.log("编辑");
       			   // upload.info.vedioCategoryPId=Id;
        		}
        		
        	}else{
        		upload.sclass=data.vedioCategoryList;
        		console.log(upload.sclass);
        		if(typeof(sId)=="undefined"){//上传时选择默认的第一个值
        			console.log("上传");
        			if(upload.sclass.length>0){
            			upload.info.vedioCategoryId=upload.sclass[0].id;
            		}else{
            			upload.info.vedioCategoryId="";
            		}
        		}else{
        			console.log("编辑");
        			upload.info.vedioCategoryId=sId;
        			upload.info.vedioCategoryPId=Id;	
        			console.log(upload.info.vedioCategoryId);
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
        	console.log(2);
        	var vedio=data.vedio;
        	var info=upload.info;
        	info.vedioName=vedio.vedioName;
            info.vedioNotes=vedio.vedioNotes;
            info.isFree=vedio.isFree;
            info.money=vedio.money;
            info.vedioCategoryPId=vedio.vedioCategoryPId;
           classLoad(0,vedio.vedioCategoryId);//加载父类
           classLoad(vedio.vedioCategoryPId,vedio.vedioCategoryId);//加载子类
        }
	},function(){
		alert("wrong");
	})
} 























function check(){
	//alert(2);
	//return true;
	
}