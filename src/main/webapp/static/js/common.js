$.ajaxs=function(url,data,successfn,errorfn){
	data=(data==null||data==""||typeof(data)=="undefined")?{"data":new Date().getTime()}:data;
	$.ajax({
		url:url,
		type:"post",
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
