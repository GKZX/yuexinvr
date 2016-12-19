package org.yuexin.app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.yuexin.controller.BaseController;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.VedioCategoryAppDTO;
import org.yuexin.service.VedioCategoryAppService;
import org.yuexin.service.VedioCategoryService;
import org.yuexin.service.VedioService;
import org.yuexin.util.ErrorAppEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**

* @Description: APP视频模块相关接口

* @author liuqin

* @date 2016-12-19 上午11:05:34

*
 */
@Controller
public class VedioAPPController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(VedioAPPController.class);  
	@Autowired
	private VedioService vedioService;
	@Autowired
	private VedioCategoryAppService vedioCategoryAppService;

	/**
	 * 视频分类大类
	 * @param type 类型:1-置顶到首页;0-更多;
	 * @return
	 */
	@RequestMapping("/vedioApp/getVedioCateGory")
	@ResponseBody
	public JSONObject getVedioCateGory(Integer type) {
		JSONObject data = new JSONObject();
		LOG.info("查询视频分类type:"+type);
		//后台的视频分类
		List<VedioCategoryAppDTO> vedioCategoryList = vedioCategoryAppService.getVedioCategorysByType(type, 0);
		data.put("vedioCategoryList", JSONArray.toJSON(vedioCategoryList));
		LOG.info("查询视频分类成功!");
		return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, data);
	}
	
	@RequestMapping("/vedioApp/getVedios")
	@ResponseBody
	public JSONObject getVedios(Integer vedioCategoryId) {
		JSONObject data = new JSONObject();
		return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, data);
	}
}
