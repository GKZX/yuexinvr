package org.test.yuexin.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.yuexin.controller.UserController;
import org.yuexin.model.User;
import org.yuexin.util.ErrorEnums;
import org.yuexin.util.ErrorEnums;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONObject;

public class UserControllerTest extends BaseTransactionTestCase {
	@Autowired
	private UserController userController;

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testGetUsers(HttpServletRequest request) {
		JSONObject object = userController.getUsers(null, null, request);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testGetUsers1(HttpServletRequest request) {
		JSONObject object = userController.getUsers(1, 10, request);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorEnums.SUCCESS.getCode());

		assertNotNull(object.get("data"));// 返回的数据不为空
		JSONObject data = (JSONObject) object.get("data");

		assertNotNull(data.get("userSize"));

		Object userListObj = data.get("userList");
		assertNotNull(userListObj);
		List<User> userList = (List<User>) userListObj;
		if (!CollectionUtils.isEmpty(userList)) {
			for (User user : userList) {
				assertNotNull("手机号码为空，用户ID："+user.getId(), user.getPhone());
				assertNotNull("新增时间为空，用户ID："+user.getId(), user.getAddTime());
				assertNotNull("用户名为空，用户ID："+user.getId(), user.getUserName());
			}
		}
	}

}
