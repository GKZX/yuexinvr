var video=new Vue({
	el:"#videoManager",
	data:{
		message:"",
		isEdit:false
	},
	methods:{
		editVideo:function(){
		   this.isEdit=true;
		},
		backVideo:function(){
			this.isEdit=false;
		},
		checkVideo:function(index){
			this.message.lists[index].checked=!this.message.lists[index].checked;
		},
		delVideo:function(){
			var lists=this.message.lists;
			var removeList=[];
			for(var i=0;i<lists.length;i++){
				if(lists[i].checked==true){
					lists.splice(i,1);
					i--;
				}
			}
		}
	}
})
$(function(){
	loadData();
})
function loadData(){
	$.ajax({
		type:"get",
		url:"json/video.json",
		success:function(data){
			for(var i=0;i<data.lists.length;i++){
				data.lists[i].checked="";
			}
			video.message=data;	
		}
	})
}