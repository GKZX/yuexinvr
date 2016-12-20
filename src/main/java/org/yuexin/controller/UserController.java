package org.yuexin.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuexin.model.User;
import org.yuexin.service.UserService;
import org.yuexin.util.CollectionUtil;
import org.yuexin.util.ErrorAppEnums;
import org.yuexin.util.MD5Util;
import org.yuexin.util.RandomNumeric;
import org.yuexin.util.yunxin.YunUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
public class UserController {

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
		// 手机号不能为空
		if (StringUtils.isEmpty(phone)) {
			return ErrorAppEnums.getResult(ErrorAppEnums.PHONE_ISNULL, null, null);
		}

		// 用户存在则不能注册
		if (null != userService.getUser(phone)) {
			return ErrorAppEnums.getResult(ErrorAppEnums.USER_EXISTS, null, null);
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
	 * app用户注册
	 * 
	 * @param userMap
	 *            app用户信息
	 * @param response
	 *            http状态
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/register", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject appRegister(String username, String password) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return ErrorAppEnums.getResult(ErrorAppEnums.NAMEORPASW_ISNULL, null, null);
		}

		User user = new User();
		user.setUserName(username);
		user.setPhone(username);
		user.setPassword(MD5Util.replaceMD5(password));
		user.setSysFlag((byte) 1);
		user.setAddTime(new Date());

		// 检查app用户是否存在
		if (null != userService.getUser(user.getPhone())) {
			return ErrorAppEnums.getResult(ErrorAppEnums.USER_EXISTS, null, null);
		}

		if (userService.addUser(user)) {// 注册成功
			return ErrorAppEnums.getResult(ErrorAppEnums.SUCCESS, "注册", userService.getUser(user.getUserName()));
		}
		return ErrorAppEnums.getResult(ErrorAppEnums.SERVER_ERROR, null, null);
	}
}
