package org.yuexin.controller;


import org.yuexin.model.User;
import org.yuexin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView showLogin(){
		ModelAndView ModelAndView=new ModelAndView("/showLogin");
		return ModelAndView;
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String userName,String password){
		if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
			
		}
		User userInfos = userService.getUsers();
		return userInfos;
	}
}
