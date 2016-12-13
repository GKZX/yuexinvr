var upload=new Vue({
	el:"#uploadManage",
	data:{
		isfee:false
	},
	methods:{
		choise:function(){
			
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
})
function loadClass(){
	var curl="getVedioCateGory";
	var ctype="get";
	var classData={
		"vedioCategoryPId":0
	};
	$.ajaxs(curl,ctype,classData,function(data){
		console.log(data);
//		if(data.errorCode==20001){//鐢ㄦ埛鍚嶆垨鑰呭瘑鐮侀敊璇�
//			self.isWrong=true;
//		}else if(data.errorCode==10000){//鐧婚檰鎴愬姛
//			self.isWrong=false;
//			window.location.href="index";
//		}
	},function(){
		alert("wrong");
	})
}