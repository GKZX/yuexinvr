package org.yuexin.controller;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.yuexin.model.SysUser;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioCategory;
import org.yuexin.service.VedioCategoryService;
import org.yuexin.service.VedioService;
import org.yuexin.util.ErrorEnums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
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
	@Autowired
	private VedioCategoryService vedioCategoryService;
	
	/**
	 * 视频分类
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/vedio/getVedioCateGory")
	@ResponseBody
	public JSONObject getVedioCateGory(Integer vedioCategoryPId){
		JSONObject result = new JSONObject();
		if(vedioCategoryPId == null){
			vedioCategoryPId = 0;
		}
		List<VedioCategory> vedioCategoryList = vedioCategoryService.getVedioCategoryByPId(vedioCategoryPId);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		result.put("vedioCategoryList", JSONArray.toJSON(vedioCategoryList));
		return result;
	}
	
	/**
	 * 新增视频
	 * @param vedioCategoryId
	 * @param isFree
	 * @param money
	 * @param vedioName
	 * @param vedioImgUrl
	 * @param vedioUrl
	 * @return
	 */
	@RequestMapping("/vedio/addVedio")
	@ResponseBody
	public JSONObject addVedio(Integer vedioCategoryId,Short isFree,Integer money,String vedioName,String vedioImgUrl,String vedioUrl){
		JSONObject result = new JSONObject();
		if(vedioCategoryId == null || isFree == null || StringUtils.isBlank(vedioName) || StringUtils.isBlank(vedioImgUrl) || StringUtils.isBlank(vedioUrl)){
			result.put("errorCode", ErrorEnums.PARAM_ERROR.getCode());
			result.put("errorMsg", ErrorEnums.PARAM_ERROR.getMsg());
			return result;
		}
		vedioService.addVedio(vedioCategoryId, isFree, money, vedioName, vedioImgUrl, vedioUrl);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		return result;
	}
	
	
}
