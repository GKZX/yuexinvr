var upload = new Vue ({
	el: "#uploadManage",
	data: {
		fclass: [],                         //下拉列表中所有的大分类元素
		sclass: [],                         //下拉列表中大分类对应的小分类元素
		isfee: false,                       //是否收费 默认不收费
		videoStr: "",                       //利用随机字符串形式生成的新的视频名字
		imgStr: "",                         //利用随机字符串形式生成的新的图片名字
		bigId: "",                          //上传模式所属的视频大分类ID
		disabled: true,                     //提交按钮是否可用  上传默认状态不可用
		videoProgress: 0,                   //视频上传进度
		videoProgressShow: false,           //是否显示视频上传进度条 默认不显示
		info: {                             //上传视频要提交的参数
			type: 0,                        //操作类型: 0-上传;1-编辑;
			vedioId: "",                    //视频ID（编辑时必传）
			vedioName: "",                  //视频名称
			vedioNotes: "",                 //视频简介
			vedioImgUrl: "img/album.png",   //视频封面地址
			vedioUrl: "",                   //视频地址
			isFree: 0,                      //是否免费  0 免费  1收费
			money: "",                      //收取费用  收费时必填
			vedioCategoryPId: "",           //视频大分类ID
			vedioCategoryId: ""             //视频小分类ID
		}
	},
	methods: {
		//父级分类改变触发子类改变
		choise: function (e){
			var val = e.currentTarget.value;
			classLoad(val);
		},
		//点击提交
		upload: function(){
			var self = this;
		    if (this.sclass.length == 0) {//当大分类下没有子类时将下拉列表的子类置为空
		    	this.info.vedioCategoryId = "";
		    }
		    if (check()) {//检验合格再进行提交
		    	 var uurl = "/vedio/addOrEditVedio";
			     var utype = "post";
			     var upData = this.info;
			     upData.money = 100*this.info.money;//单位的转换  提交到数据库单位为分
			     $.ajaxs(uurl, utype, upData, function(data){
			        if(data.errorCode == 10000){//成功
			        	upData.money = "";
			        	window.location.href = "video.html?id=" + self.info.vedioCategoryPId;//自动跳转到相对应分类的视频列表页
			        } else if(data.errorCode == 2002){
			        	top.location.href = "showLogin";
			        }
				 },function (){
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
	//编辑页获得传过来的要编辑的视频id
	var id = getParam("id");
	//从视频列表页获得操作类型
	var type = getParam("type");
	if(type == 1){//为编辑状态
		EditData(id);	
	}else{//为上传状态加载下拉列表
		classLoad(0);//大类列表数据
		classLoad(getParam("bigId"));	//对应的小类列表数据
	}
	upload.info.vedioId = id;
	upload.info.type = type;
	//进行视频数据非空和合法性检验
	$(".form-control").blur(function(){
		check();
	});
	$(".form-control").change(function(){
		check();
	})
})

//加载视频分类
function classLoad(Id, sId){//父类ID  子类ID
	var curl = "vedio/getVedioCateGory";
	var ctype = "get";
	var classData = {
		"vedioCategoryPId": Id
	};
	$.ajaxs(curl, ctype, classData, function(data){
        if (data.errorCode == 10000) {//成功
        	if(Id == 0){//父分类元素的渲染
        		upload.fclass = data.vedioCategoryList;
        		if(typeof(sId) == "undefined"){//上传模式
        			upload.info.vedioCategoryPId = getParam("bigId");
        		}  		
        	}else{//根据传进来的父类ID来加载相应的子类ID
        		upload.sclass = data.vedioCategoryList;
        		if(typeof(sId) == "undefined"){//上传模式
        			if (upload.sclass.length > 0){//有子类则默认选中其子类的第一个
            			upload.info.vedioCategoryId = upload.sclass[0].id;
            		}else {//没有子类则子类下拉列表置为空
            			upload.info.vedioCategoryId = "";
            		}
        		}else {//编辑模式
        			upload.info.vedioCategoryId = sId;//子类ID
        			upload.info.vedioCategoryPId = Id;//父类ID
        		}	
        	}
        } else if (data.errorCode == 20002){//未登录状态
        	top.location.href = "showLogin";
        	console.log('未登录');
        }
	},function (){
		alert("服务器错误");
	})
}

//编辑加载数据
function EditData(Id){
	var vurl = "/vedio/getVedio";
	var vtype = "get";
	var vedioData = {
		"vedioId": Id
	};
	$.ajaxs(vurl, vtype, vedioData, function(data){
        if(data.errorCode == 10000){//成功
        	var vedio = data.vedio;
        	var info = upload.info;
        	info.vedioName = vedio.vedioName;
            info.vedioNotes = vedio.vedioNotes;
            info.isFree = vedio.isFree;
            info.money = vedio.money/100;
            info.vedioCategoryPId = vedio.vedioCategoryPId;
            info.vedioImgUrl = vedio.vedioImgUrl;
            info.vedioUrl = vedio.vedioUrl;
            classLoad(0, vedio.vedioCategoryId);//加载父类
            classLoad(vedio.vedioCategoryPId, vedio.vedioCategoryId);//加载子类
            check();//加载完数据检验
        } else if(data.errorCode == 20002) {
        	top.location.href = "showLogin";
        	
        }
	},function () {
		alert("服务器错误");
	})
} 

//数据非空检验
function check(){
	var vedio = upload.info;
	var validate = "";
	var reg = /^[0-9]*$/;
	if(vedio.vedioName == ""||vedio.vedioNotes == ""||vedio.vedioUrl == ""||vedio.vedioImgUrl == ""||vedio.vedioCategoryPId == ""||(vedio.isFree == 1&&vedio.money == "")) {
		validate = false;
		upload.disabled = true;
	}else {
		if(reg.test(vedio.money)){
			if(vedio.isFree == 1 && vedio.money <= 0) {//收费时费用小于等于0不合法
				validate = false;
				upload.disabled = true;
			}else {
				validate = true;
				upload.disabled = false;
			}
		}else {//费用有包含其他字符不合法
			validate = false;
			upload.disabled = true;
		}
	}
	return validate;
}

//视频和图片上传调用阿里云模块
var uploader = new VODUpload ({
    // 文件上传失败
    'onUploadFailed': function (fileName, code, message) {
        console.log("onUploadFailed: " + fileName + code + "," + message);
        if (fileType(fileName) == "视频"){//上传视频
        	$(".videoChoise").text("视频上传失败请重新上传");
        }
    },
    // 文件上传完成
    'onUploadSucceed': function (fileName,callback) {
        console.log("onUploadSucceed: " + fileName);
        if (fileType(fileName) == "视频"){
        	upload.videoProgressShow = false;
        	upload.info.vedioUrl = "http://"+$(".bucket").text() + "." + $(".endpoint").text().substr(7) + "/" + upload.videoStr;
        	$(".videoChoise").text("已上传：" + $(".upVideoFile").val());
        	check();
        }
        if (fileType(fileName) == "封面"){
        	 upload.info.vedioImgUrl="http://" + $(".bucket").text() + "."+$(".endpoint").text().substr(7) + "/" + upload.imgStr;
        	 $(".upload-cover-preview").html("<img src='" + upload.info.vedioImgUrl + "' class='imgpreview-image'>");
        	 check();
        }
    },
    // 文件上传进度
    'onUploadProgress': function (fileName, totalSize, uploadedSize) {
        console.log("file:" + fileName + ", " + totalSize, uploadedSize, "percent:", Math.ceil(uploadedSize * 100 / totalSize));
        if (fileType(fileName) == "视频"){//上传视频
        	upload.videoProgressShow = true;
        	upload.videoProgress = Math.ceil(uploadedSize * 100 / totalSize) + "%";
        }
    },
    // token超时
    'onUploadTokenExpired': function (callback) {
        console.log("onUploadTokenExpired");
        if (fileType(fileName) == "视频"){//上传视频
           $(".videoChoise").text("上传超时请重新上传");
        }
    }
});

uploader.init($(".accessKeyId").text(),$(".secretAccessKey").text());

//视频上传
document.getElementById("upVideoFile").addEventListener('change', function (event) {
    for(var i=0; i<event.target.files.length; i++) {
    	var fileName = event.target.files[i].name;
    	var type = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
    	if(type!=".rm"&&type!=".rmvb"&&type!=".wmv"&&type!=".avi"&&type!=".mpg"&&type!=".mpeg"&&type!=".mp4"){
    		$('#videoWarn').modal('show');
    	}else{
    		var timestamp = new Date().getTime();
    		var string = generateMixed(8);   
    		var newName = timestamp+string+type;
    		upload.videoStr = newName;
            uploader.addFile (event.target.files[i], $(".endpoint").text(), $(".bucket").text(), newName);
    	}	
    }
    uploader.startUpload();
});

//封面上传
document.getElementById("upImgFile").addEventListener('change', function (event) {
	for(var i=0; i<event.target.files.length; i++) {
		var fileName = event.target.files[i].name;
    	var type = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
	    if (type!='.bmp'&&type!='.png'&&type!='.gif'&&type!='.jpg'&&type!='.jpeg'){
	    	$('#imgWarn').modal('show');
	    } else{
	    	var imgSize = event.target.files[i].size;
	        if(imgSize <= 4*1024*1024){//图片大小不能超过4M
		   	    //生成时间戳加八位随机数的名字
			    var timestamp = new Date().getTime();
		   		var string = generateMixed(8);   
		   		var newName = timestamp+string+type;
				uploader.addFile(event.target.files[i], $(".endpoint").text(), $(".bucket").text(), newName);
				upload.imgStr = newName;
				var file = this.files[0];
//				console.log(file);
//				function imgShow(file){
//					var reader=new FileReader();
//					reader.onload=function(e){
//						var url=e.target.result;
//		    			$(".upload-cover-preview").html("<img src='"+url+"' class='imgpreview-image'>");	
//					}
//					reader.readAsDataURL(file);	
//				}
	        } else{
	        	$('#imgWidthWarn').modal('show');
	        }
		} 
     }   
	uploader.startUpload();
});

//判断上传文件类型
 function fileType(fileName){
	 var fileType = "";
	 var videoVal = $("#upVideoFile").val();
	 var imgVal = $("#upImgFile").val();
	 if(videoVal.indexOf(fileName) > -1){
		 fileType = "视频";
	 }
	 if(imgVal.indexOf(fileName) > -1){
		 fileType = "封面";
	 }
	 return fileType;
 }










