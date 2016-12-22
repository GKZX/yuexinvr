$.ajaxs=function(url,type,data,successfn,errorfn){
	data=(data==null||data==""||typeof(data)=="undefined")?{"data":new Date().getTime()}:data;
	$.ajax({
		url:"http://localhost:8080/yuexinvr/"+url,
		type:type,
		data:data,
		dataType:"json",
		async:true,
		timeout:5000,
		success:function(d){
			successfn(d);
		},
		error:function(e){
			errorfn(e);
		}
	})
}
//获取地址栏参数	 
function getParam(name){  //获取参数
	var url=window.location.search;  //获取问号之后的字0符
	var reg=new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	if(url!=null && url.toString().length>1)
	{ 
	var r=url.substr(1).match(reg);
	if(r!=null)return unescape(r[2]); return null;
	}
};
//生成随机数
function GetRandomNum(Min,Max)
{   
	var Range = Max - Min;   
	var Rand = Math.random();   
	return(Min + Math.round(Rand * Range));   
} 
//生成随机字符串
var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
function generateMixed(n) {
     var res = "";
     for(var i = 0; i < n ; i ++) {
         var id = Math.ceil(Math.random()*35);
         res += chars[id];
     }
     return res;
}
//判断图片大小和宽高
function checkImgPX(ths, width, height) {  
    var img = null;  
    img = document.createElement("img");  
    document.body.insertAdjacentElement("beforeEnd", img); // firefox不行  
    img.style.visibility = "hidden";   
    img.src = ths.value;  
    var imgwidth = img.offsetWidth;  
    var imgheight = img.offsetHeight;  
       
    alert(imgwidth + "," + imgheight);  
       
    if(imgwidth != width || imgheight != height) {  
        alert("图的尺寸应该是" + width + "x"+ height);  
        ths.value = "";  
        return false;  
    }  
    return true;  
} 
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
