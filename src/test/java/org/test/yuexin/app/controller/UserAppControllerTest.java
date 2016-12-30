package org.test.yuexin.app.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuexin.app.controller.UserAppController;
import org.yuexin.model.dto.BannerAppDTO;
import org.yuexin.util.ErrorAppEnums;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONObject;

public class UserAppControllerTest extends BaseTransactionTestCase {
	@Autowired
	private UserAppController userAppController;

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testAppSendCode() {
		JSONObject object;
		try {
			object = userAppController.appSendCode(null);
			assertNotNull(object);
			assertEquals(object.get("code"), ErrorAppEnums.PHONE_ISNULL.getCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 手机号码格式错误
	 */
	@Test
	public void testAppSendCode1() {
		JSONObject object;
		try {
			object = userAppController.appSendCode("3232131232132");
			assertNotNull(object);
			assertEquals(object.get("code"), ErrorAppEnums.PHONE_FORMAT_ERROR.getCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 正常手机号码，应收到短信成功
	 */
	@Test
	public void testAppSendCode2() {
		String phone = "15123123123";
		JSONObject object;
		try {
			object = userAppController.appSendCode(phone);
			assertNotNull(object);
			assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
			assertEquals(object.get("phone"), phone);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * type为空，返回参数错误
	 */
	@Test
	public void testAppRegister() {
		JSONObject object = userAppController.appRegister("13296721646", null, null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 用户名（手机号码）为空，用户名或密码不能为
	 */
	@Test
	public void testAppRegister1() {
		JSONObject object = userAppController.appRegister(null, "dfsdfd", 1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ISNULL.getCode());
	}

	/**
	 * 密码为空，用户名或密码不能为空
	 */
	@Test
	public void testAppRegister2() {
		JSONObject object = userAppController.appRegister("13296721646", null, 1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ISNULL.getCode());
	}

	/**
	 * 已存在的用户，注册，提示用户已存在
	 */
	@Test
	public void testAppRegister3() {
		JSONObject object = userAppController.appRegister("15123123123", "ssss", 1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.USER_EXISTS.getCode());
	}

	/**
	 * 已存在的用户，修改密码，更新数据库，提示成功
	 */
	@Test
	public void testAppRegister4() {
		JSONObject object = userAppController.appRegister("15123123123", "ssss", 2);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
	}

	/**
	 * 新用户，注册，新增用户，提示成功
	 */
	@Test
	public void testAppRegister5() {
		String phone = "15123123123";
		JSONObject object = userAppController.appRegister(phone, "ssss", 1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertEquals(object.get("phone"), phone);
	}

	/**
	 * 新用户，修改密码，默认注册新增用户，提示成功
	 */
	@Test
	public void testAppRegister6() {
		String phone = "15123123129";
		JSONObject object = userAppController.appRegister(phone, "ssss", 2);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertEquals(object.get("phone"), phone);
	}

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testAppLogin() {
		JSONObject object = userAppController.appLogin(null, null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ISNULL.getCode());
	}

	/**
	 * 手机号码为空，应该返回参数错误
	 */
	@Test
	public void testAppLogin1() {
		JSONObject object = userAppController.appLogin(null, "ssss");
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ISNULL.getCode());
	}

	/**
	 * 密码为空，应该返回参数错误
	 */
	@Test
	public void testAppLogin2() {
		JSONObject object = userAppController.appLogin("13296721645", null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ISNULL.getCode());
	}

	/**
	 * 密码错误
	 */
	@Test
	public void testAppLogin3() {
		JSONObject object = userAppController.appLogin("13296721645", "ss");
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.NAMEORPASW_ERROR.getCode());
	}
	
	/**
	 * 用户名密码正确，登录成功
	 */
	@Test
	public void testAppLogin4() {
		String phone = "13296721645";
		JSONObject object = userAppController.appLogin(phone, "111111");
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertEquals(object.get("phone"), phone);
		assertNotNull(object.get("id"));// 用户ID
	}
	
	/**
	 * app版本信息，正常查询
	 */
	@Test
	public void testGetVersion() {
		JSONObject object = userAppController.getVersion();
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertNotNull(object.get("versionName"));
	}
	
	/**
	 * banner信息，正常查询
	 */
	@Test
	public void testGetBanners() {
		JSONObject object = userAppController.getBanners();
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertNotNull(object.get("data"));// 返回的数据不为空
		
		JSONObject data = (JSONObject) object.get("data");
		Object bannerListObj = data.get("bannerList");
		assertNotNull(bannerListObj);
		List<BannerAppDTO> bannerList = (List<BannerAppDTO>) bannerListObj;
		assertThat(bannerList.size(), greaterThanOrEqualTo(1));// banner最少一条
	}
}
