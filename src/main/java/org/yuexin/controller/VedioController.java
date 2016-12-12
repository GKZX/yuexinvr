package org.yuexin.controller;


import javax.servlet.http.HttpServletResponse;

import org.yuexin.model.SysUser;
import org.yuexin.service.SysUserService;
import org.yuexin.service.VedioService;
import org.yuexin.util.ErrorEnums;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**

* @Description: 视频

* @author liuqin

* @date 2016-12-12 下午2:27:05

*/
@Controller
public class VedioController {
	@Autowired
	private VedioService vedioService;
	
}
