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
import java.util.HashMap;
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
	public Map<String, Object> appLogin(@RequestBody Map<String, Object> loginParam, HttpServletResponse response) {
		Map<String, Object> msg;
		//参数不能为空
		if (CollectionUtil.isEmpty(loginParam)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "用户名或密码不能为空");
			msg.put("data", null);
			return msg;
		}
		User user = new User();
		user.setUserName(String.valueOf(loginParam.get("username")));
		user.setPassword(MD5Util.replaceMD5(String.valueOf(loginParam.get("password"))));

		User loginUser = userService.getUser(user);
		if (null == loginUser) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "用户名或密码错误");
			msg.put("data", null);
			return msg;
		} else {
			msg = new HashMap<String, Object>();
			msg.put("code", 0);
			msg.put("msg", "登录成功");
			msg.put("data", loginUser);
			return msg;
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
	public Map<String, Object> appSendCode(@RequestBody Map<String, Object> phoneMap, HttpServletResponse response) throws IOException {
		Map<String, Object> msg;
		//手机号不能为空
		if (CollectionUtil.isEmpty(phoneMap) ||
				StringUtils.isEmpty(String.valueOf(phoneMap.get("phone")))) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "手机号不能为空");
			msg.put("data", null);
			return msg;
		}

		String phone = phoneMap.get("phone").toString();

		//用户存在则不能注册
		if (null != userService.getUser(phone)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "该手机号已注册");
			msg.put("data", null);
			return msg;
		}

		String code = RandomNumeric.random();

		String[] phones = {phone};
		String[] codes = {code};
		String data = YunUtils.sendTemplateMessage(phones, codes);
		Map<String, Object> msgMap = new ObjectMapper().readValue(data, Map.class);
		if (!"200".contains(String.valueOf(msgMap.get("code")))) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", -1);
			msg.put("msg", "短信发送失败");
			msg.put("data", null);
			return msg;
		} else {
			response.setStatus(HttpServletResponse.SC_OK);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("phone", phone);
			params.put("code", code);

			msg = new HashMap<String, Object>();
			msg.put("code", 0);
			msg.put("msg", "短信验证码发送成功");
			msg.put("data", params);
			return msg;
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
	public Map<String, Object> appRegister(@RequestBody Map<String, Object> userMap, HttpServletResponse response) {
		Map<String, Object> msg;
		if (CollectionUtil.isEmpty(userMap)) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "用户信息不能为空");
			msg.put("data", null);
			return msg;
		}

		User user = new User();
		user.setUserName(String.valueOf(userMap.get("username")));
		user.setPhone(String.valueOf(userMap.get("phone")));
		user.setPassword(MD5Util.replaceMD5(String.valueOf(userMap.get("password"))));
		user.setReligiousBelief(Integer.parseInt(String.valueOf(userMap.get("religiousBelief"))));
		user.setSysFlag((byte) 1);
		user.setAge(Integer.parseInt(String.valueOf(userMap.get("age"))));
		user.setJob(String.valueOf(userMap.get("job")));
		user.setAddTime(new Date());

		//检查app用户是否存在
		if (null != userService.getUser(user.getPhone())) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			msg = new HashMap<String, Object>();
			msg.put("code", 1);
			msg.put("msg", "用户已注册");
			msg.put("data", null);
			return msg;
		} else {
			if (userService.addUser(user)) {
				response.setStatus(HttpServletResponse.SC_OK);
				msg = new HashMap<String, Object>();
				msg.put("code", 0);
				msg.put("msg", "用户注册成功");
				msg.put("data", userService.getUser(user.getUserName()));
				return msg;
			} else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				msg = new HashMap<String, Object>();
				msg.put("code", -1);
				msg.put("msg", "服务器内部错误");
				msg.put("data", null);
				return msg;
			}
		}
	}
}
