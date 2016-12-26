package org.test.yuexin.app.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuexin.app.controller.VedioAppController;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.VedioCategoryAppDTO;
import org.yuexin.util.ErrorAppEnums;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class VedioAppControllerTest extends BaseTransactionTestCase {
	@Autowired
	private VedioAppController vedioAppController;

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testGetVedioCategory() {
		JSONObject object = vedioAppController.getVedioCategory(null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * type范围内0,1正常情况返回数据
	 */
	@Test
	public void testGetVedioCategory1() {
		for (int i = 0; i < 2; i++) {
			JSONObject object = vedioAppController.getVedioCategory(i);
			assertNotNull(object);
			assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());// 调用成功
			assertNotNull(object.get("data"));// 返回的数据不为空

			JSONObject data = (JSONObject) object.get("data");
			assertNotNull(data.get("vedioCategoryList"));// 返回的视频分类数组不为空

			JSONArray vedioCategoryList = (JSONArray) data.get("vedioCategoryList");
			assertThat("type的值为:" + i, vedioCategoryList.size(), greaterThanOrEqualTo(1));// 返回的视频分类数组数据大于等于1条
		}

	}

	/**
	 * type超出预期值0,1应该返回分类为空
	 */
	@Test
	public void testGetVedioCategory2() {
		JSONObject object = vedioAppController.getVedioCategory(10);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());// 调用成功
		assertNotNull(object.get("data"));// 返回的数据不为空

		JSONObject data = (JSONObject) object.get("data");
		assertNull("超出预期的type值", data.get("vedioCategoryList"));// 返回的视频分类数组为空
	}
	
	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testGetCategoryVedios(){
		JSONObject object = vedioAppController.getCategoryVedios(null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}
	
	@Test
	public void testGetCategoryVedios1(){
		JSONObject object = vedioAppController.getCategoryVedios(1);
		System.out.println(object);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertNotNull(object.get("data"));// 返回的数据不为空

		JSONObject data = (JSONObject) object.get("data");
		assertNotNull(data.get("vedioCategory"));// 分类
		
		VedioCategory vedioCategory = (VedioCategory) data.get("vedioCategory");
		assertEquals(vedioCategory.getVedioCategoryName(), "宗教文化");
		
		
	}

}
