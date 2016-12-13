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
