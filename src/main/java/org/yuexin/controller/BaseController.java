package org.yuexin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.yuexin.model.SysUser;

@Controller
public class BaseController {
	
	public SysUser getSysUser(HttpServletRequest request){
		Object obj = getSession(request,"sysUser");
		if(obj != null){
			return (SysUser) obj;
		}
		return null;
	}
	
	public void setSession(HttpServletRequest request, String key, Object value) {
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}
	
	public Object getSession(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		return session.getAttribute(key);
	}
	
	public void removeSession(HttpServletRequest request, String key) {
		HttpSession session = request.getSession();
		session.removeAttribute(key);
	}
}
