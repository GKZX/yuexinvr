$(function(){
	// 基于准备好的dom，初始化echarts实例
    var incomeChart = echarts.init(document.getElementById("incomeGraph"));
	option = {
	    title: {
	        show:false
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['收入'],
	        bottom:"0px",
	        itemWidth:60,
	        textStyle:{
	        	color: '#cf0b18'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '15%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
	    },
	    yAxis: {
	    	min:0,
	    	max:100,
	        type: 'value'
	    },
	    series: [
	        {
	            name:'收入',
	            type:'line',
	            stack: '总量',
	            data:[20, 32, 11, 34, 90, 23, 21,33,44,33,11,58,34,54,23,23, 21,33,44,33,11,28,34,54]
	        }
	    ]
	};
	incomeChart.setOption(option);
})
