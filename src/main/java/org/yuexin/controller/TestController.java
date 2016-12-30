package org.yuexin.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.yuexin.model.SysUser;
import org.yuexin.service.SysUserService;
import org.yuexin.util.ErrorEnums;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class TestController extends BaseController{


	
	/**
	 * 用户测试页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/test/user_test")
	public ModelAndView userTest(){
		ModelAndView ModelAndView=new ModelAndView("/test/user_test");
		return ModelAndView;
	}
	/**
	 * 公用方法测试页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/test/common_test")
	public ModelAndView commonTest(){
		ModelAndView ModelAndView=new ModelAndView("/test/common_test");
		return ModelAndView;
	}
	/**
	 * 登录测试页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/test/login_test")
	public ModelAndView loginTest(){
		ModelAndView ModelAndView=new ModelAndView("/test/login_test");
		return ModelAndView;
	}
	/**
	 * 视频测试页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/test/video_test")
	public ModelAndView videoTest(){
		ModelAndView ModelAndView=new ModelAndView("/test/video_test");
		return ModelAndView;
	}
	/**
	 * 上传页面
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/test/upload_test")
	public ModelAndView uploadTest(){
		ModelAndView ModelAndView=new ModelAndView("/test/upload_test");
		return ModelAndView;
	}
}