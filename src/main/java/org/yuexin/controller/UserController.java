package org.yuexin.controller;


import com.alibaba.druid.util.StringUtils;
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
import org.yuexin.util.MD5Util;
import org.yuexin.util.RandomNumeric;
import org.yuexin.util.yunxin.YunUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**

* @Description: 管理员账号信息

* @author liuqin

* @date 2016-12-9 下午2:49:48

*
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable int userId){
		return "/user/showInfo";
	}
	
	@RequestMapping("/showInfos")
	public @ResponseBody Object showUserInfos(){
		User userInfos = userService.getUsers();
		return userInfos;
	}

	/**
	 * app用户登录
	 * @param loginParam 登录信息
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/login", method = RequestMethod.POST)
	@ResponseBody
	public User appLogin(@RequestBody Map<String, Object> loginParam, HttpServletResponse response) {
		//参数不能为空
		if (CollectionUtil.isEmpty(loginParam)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		User user = new User();
		user.setUserName(String.valueOf(loginParam.get("username")));
		user.setPassword(MD5Util.replaceMD5(String.valueOf(loginParam.get("password"))));

		User loginUser = userService.getUser(user);
		if (null == loginUser) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			return loginUser;
		}
	}

	/**
	 * 注册前发送短信验证码
	 * @param phoneMap 手机号
	 * @param response http状态
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/sendCode", method = RequestMethod.POST)
	@ResponseBody
	public User appSendCode(@RequestBody Map<String, Object> phoneMap, HttpServletResponse response) throws IOException {
		//手机号不能为空
		if (CollectionUtil.isEmpty(phoneMap) ||
				StringUtils.isEmpty(String.valueOf(phoneMap.get("phone")))) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return null;
		}

		String phone = phoneMap.get("phone").toString();

		//用户存在则不能注册
		if (null != userService.getUser(phone)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}

		String code = RandomNumeric.random();

		User user = new User();
		user.setUserName(phone);
		user.setPassword("");
		user.setPhone(phone);
		user.setCode(code);
		user.setSysFlag((byte) 0);
		user.setAddTime(new Date());
		if (userService.addSendCode(user)) {
			String[] phones = {phone};
			String[] codes = {code};
			String msg = YunUtils.sendTemplateMessage(phones, codes);
			Map<String, Object> msgMap = new ObjectMapper().readValue(msg, Map.class);
			if (!"200".contains(String.valueOf(msgMap.get("code")))) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
				return userService.getUser(user.getUserName());
			}
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}
	}

	/**
	 * app用户注册
	 * @param userMap app用户信息
	 * @param response http状态
	 * @return app用户信息
	 */
	@RequestMapping(value = "/app/register", method = RequestMethod.POST)
	@ResponseBody
	public User appRegister(@RequestBody Map<String, Object> userMap, HttpServletResponse response) {
		if (CollectionUtil.isEmpty(userMap)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		User user = new User();
		user.setUserName(String.valueOf(userMap.get("username")));
		user.setPhone(String.valueOf(userMap.get("phone")));
		user.setPassword(MD5Util.replaceMD5(String.valueOf(userMap.get("password"))));
		user.setReligiousBelief(Integer.parseInt(String.valueOf(userMap.get("religiousBelief"))));
		user.setSysFlag((byte) 0);
		user.setAge(Integer.parseInt(String.valueOf(userMap.get("age"))));
		user.setJob(String.valueOf(userMap.get("job")));

		//检查app用户是否存在
		if (!userService.getUserCheck(user)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			//启用帐号并将验证码清空
			user.setSysFlag((byte) 1);
			user.setCode("");
			if (userService.updateUser(user)) {
				response.setStatus(HttpServletResponse.SC_OK);
				return userService.getUser(user.getUserName());
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return null;
			}
		}
	}
}
