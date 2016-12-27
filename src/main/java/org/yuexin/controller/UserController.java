package org.yuexin.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuexin.model.AppVersion;
import org.yuexin.model.User;
import org.yuexin.model.dto.BannerAppDTO;
import org.yuexin.service.AppVersionAppService;
import org.yuexin.service.UserService;
import org.yuexin.util.ErrorAppEnums;
import org.yuexin.util.MD5Util;
import org.yuexin.util.PhoneFormatCheckUtils;
import org.yuexin.util.RandomNumeric;
import org.yuexin.util.yunxin.YunUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 管理员账号信息
 * 
 * @author liuqin
 * 
 * @date 2016-12-9 下午2:49:48
 * 
 * 
 */
@Controller
public class UserController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private AppVersionAppService appVersionAppService;
	@Autowired
	private UserService userService;

	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable int userId) {
		return "/user/showInfo";
	}

	@RequestMapping("/showInfos")
	public @ResponseBody
	Object showUserInfos() {
		User userInfos = userService.getUsers();
		return userInfos;
	}

	/**
	 * app用户登录
	 * 
	 * @param loginParam
	 *            登录信息
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject appLogin(String username, String password) {
		// 参数不能为空
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ErrorAppEnums.getResult(ErrorAppEnums.NAMEORPASW_ISNULL, null, null);
		}
		User user = new User();
		user.setUserName(username);
		user.setPassword(MD5Util.replaceMD5(password));

		User loginUser = userService.getUser(user);
		if (null == loginUser) {
			return ErrorAppEnums.getResult(ErrorAppEnums.NAMEORPASW_ERROR, null, null);
		}
		return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, "登录", loginUser);
	}

	/**
	 * 注册前发送短信验证码
	 * 
	 * @param phoneMap
	 *            手机号
	 * @param response
	 *            http状态
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/sendCode", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject appSendCode(String phone) throws IOException {
		if (StringUtils.isEmpty(phone)) {// 手机号不能为空
			return ErrorAppEnums.getResult(ErrorAppEnums.PHONE_ISNULL, null, null);
		}
		
		if (!PhoneFormatCheckUtils.isPhoneLegal(phone)) {// 手机号码格式不正确
			return ErrorAppEnums.getResult(ErrorAppEnums.PHONE_FORMAT_ERROR, null, null);
		}

		String code = RandomNumeric.random();

		String[] phones = { phone };
		String[] codes = { code };
		String data = YunUtils.sendTemplateMessage(phones, codes);
		Map<String, Object> msgMap = new ObjectMapper().readValue(data, Map.class);
		if (!"200".contains(String.valueOf(msgMap.get("code")))) {
			return ErrorAppEnums.getResult(ErrorAppEnums.MESSAGE_SEND_FAIL, null, null);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("phone", phone);
		params.put("code", code);

		return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, "短信验证码发送", params);
	}

	/**
	 * app用户注册/修改密码
	 * 
	 * @param username
	 *            用户名（手机号）
	 * @param password
	 *            密码
	 * @param type
	 *            操作类型:1-注册；2-修改
	 * @return
	 */
	@RequestMapping(value = "/app/register", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject appRegister(String username, String password, Integer type) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || type == null) {
			return ErrorAppEnums.getResult(ErrorAppEnums.NAMEORPASW_ISNULL, null, null);
		}
		LOG.info("app用户注册/修改密码username:"+username+";password:"+password+";type:"+type);
		

		User user = userService.getUser(username);
		if (user != null && type == 1) {// 用户已存在,不能注册
			return ErrorAppEnums.getResult(ErrorAppEnums.USER_EXISTS, null, null);
		}
		
		if (user != null && type == 2) {// 用户已存在，更新密码
			userService.updateUserPassword(username,password);
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, null);
		}

		// 新增用户
		user = new User();
		user.setUserName(username);
		user.setPhone(username);
		user.setPassword(MD5Util.replaceMD5(password));
		user.setSysFlag((byte) 1);
		user.setAddTime(new Date());

		if (userService.addUser(user)) {// 注册成功
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, "注册", userService.getUser(user.getUserName()));
		}
		return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
	}

	/**
	 * 查询最新版本信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/app/getVersion", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getVersion() {
		try {
			LOG.info("查询版本");
			AppVersion appVersion = appVersionAppService.selectAppVersionById();
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, appVersion);
		} catch (Exception e) {
			LOG.error("getVersion异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	/**
	 * 首页banner
	 * 
	 * @return
	 */
	@RequestMapping(value = "/app/getBanners", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getBanners() {
		try {
			LOG.info("查询首页banner");
			List<BannerAppDTO> bannerAppDTOList = new ArrayList<BannerAppDTO>();
			bannerAppDTOList.add(getBannerAppDTO(1, 2, "http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1481881974685GGB4BCYX.png",
					"http://www.baidu.com", null));
			bannerAppDTOList.add(getBannerAppDTO(2, 1, "http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482114094505LR84XPH8.jpg", null,
					1));
			bannerAppDTOList.add(getBannerAppDTO(3, 2, "http://anneprivate1.oss-cn-hangzhou.aliyuncs.com/1482136105489RQTRN5FW.jpg",
					"http://www.baidu.com", null));
			JSONObject data = new JSONObject();
			data.put("bannerList", bannerAppDTOList);
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, null, data);
		} catch (Exception e) {
			LOG.error("getVersion异常:" + e);
			return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
		}
	}

	private BannerAppDTO getBannerAppDTO(Integer id, Integer type, String imgUrl, String url, Integer vedioId) {
		BannerAppDTO bannerAppDTO = new BannerAppDTO();
		bannerAppDTO.setId(1);
		bannerAppDTO.setType(type);
		bannerAppDTO.setImgUrl(imgUrl);
		bannerAppDTO.setUrl(url);
		bannerAppDTO.setVedioId(vedioId);
		return bannerAppDTO;
	}
}
