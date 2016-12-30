package org.yuexin.app.controller;

import java.util.List;

import org.yuexin.controller.BaseController;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.VedioAppDTO;
import org.yuexin.model.dto.VedioAppsResultDTO;
import org.yuexin.model.dto.VedioCategoryAppDTO;
import org.yuexin.service.VedioAppService;
import org.yuexin.service.VedioCategoryAppService;
import org.yuexin.util.ErrorAppEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description: APP视频模块相关接口
 * 
 * @author liuqin
 * 
 * @date 2016-12-19 上午11:05:34
 * 
 * 
 */
@Controller
public class VedioAppController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(VedioAppController.class);
	@Autowired
	private VedioAppService vedioAppService;
	@Autowired
	private VedioCategoryAppService vedioCategoryAppService;

	/**
	 * 视频分类大类
	 * 
	 * @param type
	 *            类型:1-置顶到首页;0-更多;
	 * @return
	 */
	@RequestMapping(value = "/vedioApp/getVedioCategory", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getVedioCategory(Integer type) {
		if (type == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PARAM_ERROR, null, null);
		}
		try {
			JSONObject data = new JSONObject();
			LOG.info("查询视频分类type:" + type);
			// 后台的视频分类
			List<VedioCategoryAppDTO> vedioCategoryList = vedioCategoryAppService.getVedioCategorysByType(type, 0);
			data.put("vedioCategoryList", CollectionUtils.isEmpty(vedioCategoryList) ? null : JSONArray.toJSON(vedioCategoryList));
			LOG.info("查询视频分类成功!");
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, data);
		} catch (Exception e) {
			LOG.error("getVedioCateGory异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	/**
	 * 分类视频列表
	 * 
	 * @param vedioCategoryId
	 * @return
	 */
	@RequestMapping("/vedioApp/getCategoryVedios")
	@ResponseBody
	public JSONObject getCategoryVedios(Integer vedioCategoryId) {
		if (vedioCategoryId == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PARAM_ERROR, null, null);
		}
		LOG.info("分类视频列表vedioCategoryId:" + vedioCategoryId);
		try {
			JSONObject data = new JSONObject();
			int subsize = 0;// 最新视频的默认为无子类
			if(vedioCategoryId != 0){
				subsize = vedioCategoryAppService.countVedioCategoryByPId(vedioCategoryId);
			}
			VedioCategory vedioCategory = vedioCategoryAppService.getVedioCategoryById(vedioCategoryId);// 视频大类信息
			List<VedioAppsResultDTO> vedioList = vedioAppService.getVedioAppsResultDTOList(vedioCategoryId);
			data.put("vedioCategory", vedioCategory);
			data.put("vedioList", vedioList);
			data.put("type", subsize > 0 ? 1 : 0);// 是否有子类：0-无；1-有
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, data);
		} catch (Exception e) {
			LOG.error("getCategoryVedios异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	/**
	 * 单个类别所有视频
	 * 
	 * @param vedioCategoryId
	 *            分类ID
	 * @param sortType
	 *            排序类型:1-时间倒叙;2-播放量倒叙
	 * @return
	 */
	@RequestMapping("/vedioApp/getVedios")
	@ResponseBody
	public JSONObject getVedios(Integer vedioCategoryId, Integer sortType) {
		if (vedioCategoryId == null || sortType == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PARAM_ERROR, null, null);
		}
		LOG.info("查询单个类别所有视频vedioCategoryId:" + vedioCategoryId + ";sortType:" + sortType);
		try {
			JSONObject data = new JSONObject();
			VedioCategory vedioCategory = vedioCategoryAppService.getVedioCategoryById(vedioCategoryId);// 视频分类信息
			List<VedioAppDTO> vedioList = vedioAppService.selectVedioAppDTOsByVedioCategoryId(vedioCategoryId, sortType);
			data.put("vedioCategoryName", vedioCategory != null ? vedioCategory.getVedioCategoryName() : null);
			data.put("vedioList", vedioList);
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, data);
		} catch (Exception e) {
			LOG.error("getVedios异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	/**
	 * 播放视频
	 * 
	 * @param userId
	 * @param vedioId
	 * @return
	 */
	@RequestMapping("/vedioApp/playAmont")
	@ResponseBody
	public JSONObject playAmount(Integer userId, Integer vedioId) {
		if (vedioId == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PARAM_ERROR, null, null);
		}
		LOG.info("查询单个类别所有视频vedioId:" + vedioId + ";userId:" + userId);
		try {
			vedioAppService.updatePlayAmont(userId, vedioId);
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, null);
		} catch (Exception e) {
			LOG.error("playAmount异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	/**
	 * 单个视频详情
	 * 
	 * @param vedioId
	 * @return
	 */
	@RequestMapping("/vedioApp/getVedioDetail")
	@ResponseBody
	public JSONObject getVedioDetail(Integer vedioId) {
		if (vedioId == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PARAM_ERROR, null, null);
		}
		LOG.info("查询单个类别所有视频vedioId:" + vedioId);
		try {
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, vedioAppService.selectVedioAppDTOById(vedioId));
		} catch (Exception e) {
			LOG.error("getVedios异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

}
