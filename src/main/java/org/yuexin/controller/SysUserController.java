package org.yuexin.controller;


import org.yuexin.model.User;
import org.yuexin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**

* @Description: 管理员账号信息

* @author liuqin

* @date 2016-12-9 下午2:49:48

*
 */
@Controller
public class SysUserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 管理员登录页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin(ModelMap modelMap){
		return "/showLogin";
	}
	
	@RequestMapping("/login")
	public @ResponseBody Object login(){
		User userInfos = userService.getUsers();
		return userInfos;
	}
}
