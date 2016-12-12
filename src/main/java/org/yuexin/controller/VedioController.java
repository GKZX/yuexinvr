package org.yuexin.controller;



import org.yuexin.service.VedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

/**

* @Description: 视频

* @author liuqin

* @date 2016-12-12 下午2:27:05

*/
@Controller
public class VedioController {
	@Autowired
	private VedioService vedioService;
	
}
