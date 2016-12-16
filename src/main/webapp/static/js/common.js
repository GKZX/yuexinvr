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
