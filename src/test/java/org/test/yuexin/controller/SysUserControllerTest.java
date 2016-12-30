package org.test.yuexin.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuexin.controller.SysUserController;
import org.yuexin.util.ErrorEnums;

import com.alibaba.fastjson.JSONObject;

import base.BaseTransactionTestCase;


public class SysUserControllerTest extends BaseTransactionTestCase {
	@Autowired
	private SysUserController sysUserController;

	/**
	 * 用户名、密码为空，返回参数错误
	 */
	@Test
	public void testLogin() {
		JSONObject object = sysUserController.login(null, null, null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.PARAM_ERROR.getCode());
	}
	
	/**
	 * 密码错误
	 */
	@Test
	public void testLogin1() {
		JSONObject object = sysUserController.login("admin", "12", null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.USERORPASSWORD_ERROR.getCode());
	}
	
	/**
	 * 密码正确
	 */
	@Test
	public void testLogin2() {
		JSONObject object = sysUserController.login("admin", "admin123", null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());
	}

}
