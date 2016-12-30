package org.yuexin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuexin.model.SysUser;
import org.yuexin.service.SysUserService;
import org.yuexin.util.ErrorEnums;
import org.yuexin.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

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
public class SysUserController extends BaseController {
	private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 首页路由
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		SysUser sysUser = getSysUser(request);
		if (sysUser == null) {// 未登录
			return new ModelAndView("redirect:/showLogin");
		}
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("sysUser", sysUser);
		return new ModelAndView("/index", map);
	}

	/**
	 * 管理员登录页面
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/showLogin")
	public ModelAndView showLogin() {
		ModelAndView ModelAndView = new ModelAndView("/login");
		return ModelAndView;
	}

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JSONObject login(String userName, String password, HttpServletRequest request, HttpServletResponse respose) {
		JSONObject result = new JSONObject();
		if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
			result.put("errorCode", ErrorEnums.PARAM_ERROR.getCode());
			result.put("errorMsg", ErrorEnums.PARAM_ERROR.getMsg());
			return result;
		}
		LOG.info("管理后台登录userName:" + userName + ";password：" + password + ";加密password：" + MD5Util.replaceMD5(password));
		SysUser sysUser = sysUserService.selectUserByNameAndPas(userName, MD5Util.replaceMD5(password));
		if (sysUser == null) {
			result.put("errorCode", ErrorEnums.USERORPASSWORD_ERROR.getCode());
			result.put("errorMsg", ErrorEnums.USERORPASSWORD_ERROR.getMsg());
		} else {
			result.put("errorCode", ErrorEnums.SUCCESS.getCode());
			setSession(request, "sysUser", sysUser);
		}
		return result;
	}

	/**
	 * 登出/注销
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		removeSession(request, "sysUser");// 清除session
		return new ModelAndView("redirect:/index");
	}
}
