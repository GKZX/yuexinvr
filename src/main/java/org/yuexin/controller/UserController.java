package org.yuexin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.yuexin.model.SysUser;
import org.yuexin.model.User;
import org.yuexin.service.UserService;
import org.yuexin.util.ErrorEnums;
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
 * @Description: 用户信息管理
 * 
 * @author liuqin
 * 
 * @date 2016-12-14 下午3:24:24
 * 
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 用户管理页面路由
	 * 
	 * @return
	 */
	@RequestMapping("/user")
	public ModelAndView user() {
		return new ModelAndView("/user");
	}

	/**
	 * 用户信息列表
	 * @param indexPage 第几页
	 * @param pageSize 每页条数
	 * @param request
	 * @return
	 */
	@RequestMapping("/user/getUsers")
	@ResponseBody
	public JSONObject getUsers(@RequestParam(required = false, defaultValue = "1") Integer indexPage,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		SysUser sysUser = getSysUser(request);
		if (sysUser == null) {// 未登录
			result.put("errorCode", ErrorEnums.NOT_LOGIN.getCode());
			result.put("errorMsg", ErrorEnums.NOT_LOGIN.getMsg());
			return result;
		}
		List<User> userList = userService.selectUsersBy(indexPage, pageSize);
		int userSize = userService.selectUserCount();
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		result.put("userList", JSONArray.toJSON(userList));
		result.put("userSize", userSize);
		return result;
	}
}
