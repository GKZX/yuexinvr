var upload=new Vue({
	el:"#uploadManage",
	data:{
		fclass:[],
		sclass:[],
		isfee:false,
		videoStr:"",
		imgStr:"",
		vedioInfo:"",
		bigId:"",
		disabled:true,
		videoProgress:0,
		videoProgressShow:false,
		info:{
			type:0,
			vedioId:"",
			vedioName:"",
			vedioNotes:"",
			vedioImgUrl:"img/album.png",
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
			var self=this;
		    if(this.sclass.length==0){
		    	this.info.vedioCategoryId="";
		    }
		    console.log(check());
		    if(check()){//检验合格
		    	 var uurl="/vedio/addOrEditVedio";
			     var utype="post";
			     var upData=this.info;
			     upData.money=100*this.info.money;
			     $.ajaxs(uurl,utype,upData,function(data){
			    	 console.log(data);
			        if(data.errorCode==10000){//成功
			        	upData.money="";
			        	window.location.href="video.html?id="+self.info.vedioCategoryPId;
			        }
				 },function(){
					 alert("服务器错误");
				 })
		    }
		   
		}
	}
})

$(function(){
	//模拟文件选择框
	$("#updataBtn").click(function(){
	   $(".uploadFile").trigger("click");
	});
	$("#upVideoBtn").click(function(){
		$(".upVideoFile").trigger("click");
	})
	//编辑页获得传过来的id
	var id=getParam("id");
	var type=getParam("type");
	if(type==1){//为编辑状态
		EditData(id);	
	}else{//为上传状态
		classLoad(0);
		classLoad(getParam("bigId"));	
	}
	upload.info.vedioId=id;
	upload.info.type=type;
	//进行检验
	$(".form-control").blur(function(){
		check();
	});
	$(".form-control").change(function(){
		check();
	})
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
        		if(typeof(sId)=="undefined"){//上传
        			upload.info.vedioCategoryPId=getParam("bigId");
        		}  		
        	}else{
        		upload.sclass=data.vedioCategoryList;
        		if(typeof(sId)=="undefined"){//上传
        			if(upload.sclass.length>0){
            			upload.info.vedioCategoryId=upload.sclass[0].id;
            		}else{
            			upload.info.vedioCategoryId="";
            		}
        		}else{
        			upload.info.vedioCategoryId=sId;
        			upload.info.vedioCategoryPId=Id;	
        		}	
        	}
        	
        }
	},function(){
		alert("服务器错误");
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
        if(data.errorCode==10000){//成功
        	var vedio=data.vedio;
        	var info=upload.info;
        	info.vedioName=vedio.vedioName;
            info.vedioNotes=vedio.vedioNotes;
            info.isFree=vedio.isFree;
            info.money=vedio.money/100;
            info.vedioCategoryPId=vedio.vedioCategoryPId;
            info.vedioImgUrl=vedio.vedioImgUrl;
            info.vedioUrl=vedio.vedioUrl;
            classLoad(0,vedio.vedioCategoryId);//加载父类
            classLoad(vedio.vedioCategoryPId,vedio.vedioCategoryId);//加载子类
        }
	},function(){
		alert("服务器错误");
	})
} 

//数据非空检验
function check(){
	var vedio=upload.info;
	var validate="";
	var reg=/^[0-9]*$/;
	if(vedio.vedioName==""||vedio.vedioNotes==""||vedio.vedioUrl==""||vedio.vedioImgUrl==""||(vedio.isFree==1&&vedio.money=="")){
		validate=false;
		upload.disabled=true;
	}else{
		if(reg.test(vedio.money)){
			if(vedio.isFree==1&&vedio.money<=0){//收费时费用小于等于0不合法
				validate=false;
				upload.disabled=true;
			}else{
				validate=true;
				upload.disabled=false;
			}
		}else{//费用有包含其他字符不合法
			validate=false;
			upload.disabled=true;
		}
	}
	return validate;
}

//视频和图片上传调用阿里云模块
var uploader = new VODUpload({
    // 文件上传失败
    'onUploadFailed': function (fileName, code, message) {
        console.log("onUploadFailed: " + fileName + code + "," + message);
        if(fileType(fileName)=="视频"){//上传视频
        	$(".videoChoise").text("视频上传失败请重新上传");
        }
    },
    // 文件上传完成
    'onUploadSucceed': function (fileName,callback) {
        console.log("onUploadSucceed: " + fileName);
        if(fileType(fileName)=="视频"){
        	upload.videoProgressShow=false;
        	upload.info.vedioUrl="http://"+$(".bucket").text()+"."+$(".endpoint").text().substr(7)+"/"+upload.videoStr;
        	$(".videoChoise").text("已上传："+$(".upVideoFile").val());
        }
        if(fileType(fileName)=="封面"){
        	 upload.info.vedioImgUrl="http://"+$(".bucket").text()+"."+$(".endpoint").text().substr(7)+"/"+upload.imgStr;
        	 $(".upload-cover-preview").html("<img src='"+upload.info.vedioImgUrl+"' class='imgpreview-image'>")
        }
    },
    // 文件上传进度
    'onUploadProgress': function (fileName, totalSize, uploadedSize) {
        console.log("file:" + fileName + ", " + totalSize, uploadedSize, "percent:", Math.ceil(uploadedSize * 100 / totalSize));
        if(fileType(fileName)=="视频"){//上传视频
        	upload.videoProgressShow=true;
        	upload.videoProgress=Math.ceil(uploadedSize * 100 / totalSize)+"%";
        }
    },
    // token超时
    'onUploadTokenExpired': function (callback) {
        console.log("onUploadTokenExpired");
        if(fileType(fileName)=="视频"){//上传视频
           $(".videoChoise").text("上传超时请重新上传");
        }
    }
});

uploader.init($(".accessKeyId").text(),$(".secretAccessKey").text());

//视频上传
document.getElementById("upVideoFile").addEventListener('change', function (event) {
    for(var i=0; i<event.target.files.length; i++) {
    	var fileName=event.target.files[i].name;
    	var type=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
    	if(type!=".rm"&&type!=".rmvb"&&type!=".wmv"&&type!=".avi"&&type!=".mpg"&&type!=".mpeg"&&type!=".mp4"){
    		$('#videoWarn').modal('show');
    	}else{
    		var timestamp=new Date().getTime();
    		var string =generateMixed(8);   
    		var newName=timestamp+string+type;
    		console.log(newName);
    		upload.videoStr=newName;
            uploader.addFile(event.target.files[i], $(".endpoint").text(), $(".bucket").text(),newName);
    	}	
    }
    uploader.startUpload();
});

//封面上传
document.getElementById("upImgFile").addEventListener('change', function (event) {
	for(var i=0; i<event.target.files.length; i++) {
		var fileName=event.target.files[i].name;
    	var type=fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
	    if(type!='.bmp'&&type!='.png'&&type!='.gif'&&type!='.jpg'&&type!='.jpeg'){
	    	$('#imgWarn').modal('show');
	    }else{
	    	var imgSize=event.target.files[i].size;
	        if(imgSize<=4*1024*1024){//图片大小不能超过4M
		   	    //生成时间戳加八位随机数的名字
			    var timestamp=new Date().getTime();
		   		var string =generateMixed(8);   
		   		var newName=timestamp+string+type;
				uploader.addFile(event.target.files[i], $(".endpoint").text(), $(".bucket").text(),newName);
				upload.imgStr=newName;
				var file=this.files[0];
//				console.log(0);
//				console.log(file);
//				function imgShow(file){
//					var reader=new FileReader();
//					reader.onload=function(e){
//						var url=e.target.result;
//		    			$(".upload-cover-preview").html("<img src='"+url+"' class='imgpreview-image'>");	
//					}
//					reader.readAsDataURL(file);	
//				}
	        }else{
	        	$('#imgWidthWarn').modal('show');
	        }
		} 
     }   
	uploader.startUpload();
});

//判断上传文件类型
 function fileType(fileName){
	 var fileType="";
	 var videoVal=$("#upVideoFile").val();
	 var imgVal=$("#upImgFile").val();
	 console.log(fileName);
	 console.log(imgVal);
	 if(videoVal.indexOf(fileName)>-1){
		 fileType="视频";
	 }
	 if(imgVal.indexOf(fileName)>-1){
		 fileType="封面";
	 }
	 return fileType;
 }










