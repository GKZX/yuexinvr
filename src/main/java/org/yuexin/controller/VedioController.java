package org.yuexin.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description: 视频
 * 
 * @author liuqin
 * 
 * @date 2016-12-12 下午2:27:05
 */
@Controller
public class VedioController {
	@Autowired
	private VedioService vedioService;
	@Autowired
	private VedioCategoryService vedioCategoryService;

	/**
	 * 视频管理页路由
	 * 
	 * @return
	 */
	@RequestMapping("/video")
	public ModelAndView video() {
		ModelAndView ModelAndView = new ModelAndView("/video");
		return ModelAndView;
	}

	/**
	 * 视频上传页路由
	 * 
	 * @return
	 */
	@RequestMapping("/upload")
	public ModelAndView upload() {
		ModelAndView ModelAndView = new ModelAndView("/upload");
		return ModelAndView;
	}

	/**
	 * 视频分类
	 * 
	 * @param vedioCategoryPId
	 *            分类父ID
	 * @return
	 */
	@RequestMapping("/vedio/getVedioCateGory")
	@ResponseBody
	public JSONObject getVedioCateGory(Integer vedioCategoryPId) {
		JSONObject result = new JSONObject();
		if (vedioCategoryPId == null) {
			vedioCategoryPId = 0;
		}
		List<VedioCategory> vedioCategoryList = vedioCategoryService.getVedioCategoryByPId(vedioCategoryPId);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		result.put("vedioCategoryList", JSONArray.toJSON(vedioCategoryList));
		return result;
	}

	/**
	 * 新增视频
	 * 
	 * @param vedioCategoryPId
	 *            视频分类第一级ID
	 * @param vedioCategoryId
	 *            视频分类第二级ID
	 * @param isFree
	 *            是否收费：0-免费；1-收费；
	 * @param money
	 *            收费金额；
	 * @param vedioName
	 *            视频名称
	 * @param vedioNotes
	 *            视频简介
	 * @param vedioImgUrl
	 *            视频封面地址
	 * @param vedioUrl
	 *            视频地址
	 * @return
	 */
	@RequestMapping("/vedio/addVedio")
	@ResponseBody
	public JSONObject addVedio(Integer vedioCategoryPId, Integer vedioCategoryId, Short isFree, Integer money, String vedioName,
			String vedioNotes, String vedioImgUrl, String vedioUrl) {
		JSONObject result = new JSONObject();
		if (vedioCategoryPId == null || isFree == null || StringUtils.isBlank(vedioName) || StringUtils.isBlank(vedioImgUrl)
				|| StringUtils.isBlank(vedioUrl)) {
			result.put("errorCode", ErrorEnums.PARAM_ERROR.getCode());
			result.put("errorMsg", ErrorEnums.PARAM_ERROR.getMsg());
			return result;
		}
		vedioService.addVedio(vedioCategoryPId, vedioCategoryId, isFree, money, vedioName, vedioNotes, vedioImgUrl, vedioUrl);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		return result;
	}

	/**
	 * 视频列表
	 * 
	 * @param vedioCategoryId
	 *            分类ID
	 * @param searchCriteria
	 *            搜索条件
	 * @param indexPage
	 *            第几页
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@RequestMapping("/vedio/getVedios")
	@ResponseBody
	public JSONObject getVedios(Integer vedioCategoryId, String searchCriteria,
			@RequestParam(required = false, defaultValue = "1") Integer indexPage,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		JSONObject result = new JSONObject();
		if (vedioCategoryId == null) {
			result.put("errorCode", ErrorEnums.PARAM_ERROR.getCode());
			result.put("errorMsg", ErrorEnums.PARAM_ERROR.getMsg());
			return result;
		}
		List<Vedio> vedioList = vedioService.selectVedioList(vedioCategoryId, searchCriteria, indexPage, pageSize);
		int vedioSize = vedioService.selectVedioSize(vedioCategoryId, searchCriteria);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		result.put("vedioList", JSONArray.toJSON(vedioList));
		result.put("vedioSize", vedioSize);
		return result;
	}

	@RequestMapping("/vedio/deleteVedios")
	@ResponseBody
	public void deleteVedios(String[] vedioIds) {
		System.out.print(111);
	}

	public String encodeParam(String param) {
		if (StringUtils.isNotBlank(param)) {
			try {
				System.out.println(param);
				System.out.println(new String(param.getBytes("UTF-8"), "UTF-8"));
				return new String(param.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
