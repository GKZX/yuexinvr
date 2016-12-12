package org.yuexin.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yuexin.model.User;
import org.yuexin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuexin.util.CollectionUtil;
import org.yuexin.util.MD5Util;

import javax.servlet.http.HttpServletResponse;
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
}
