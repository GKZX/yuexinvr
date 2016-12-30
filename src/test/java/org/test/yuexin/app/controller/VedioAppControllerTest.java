package org.test.yuexin.app.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.yuexin.app.controller.VedioAppController;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.VedioAppDTO;
import org.yuexin.model.dto.VedioAppsResultDTO;
import org.yuexin.util.ErrorAppEnums;

import base.BaseTransactionTestCase;

import com.alibaba.druid.util.StringUtils;
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
	public void testGetCategoryVedios() {
		JSONObject object = vedioAppController.getCategoryVedios(null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 正常入参，测试返回的数据结构，已经对应的固定的值
	 */
	@Test
	public void testGetCategoryVedios1() {
		JSONObject object = vedioAppController.getCategoryVedios(1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
		assertNotNull(object.get("data"));// 返回的数据不为空

		JSONObject data = (JSONObject) object.get("data");

		Object vedioCategoryObj = data.get("vedioCategory");// 分类
		assertNotNull(vedioCategoryObj);
		VedioCategory vedioCategory = (VedioCategory) vedioCategoryObj;
		assertSame(vedioCategory.getVedioCategoryName(), "极限");

		Object typeObj = data.get("type");// 是否有子类
		assertNotNull(typeObj);
		assertThat((Integer) typeObj, allOf(greaterThanOrEqualTo(0), lessThanOrEqualTo(1)));// type:0-无；1-有

	}

	/**
	 * 两个入参都为空，应该返回参数错误
	 */
	@Test
	public void testGetVedios() {
		JSONObject object = vedioAppController.getVedios(null, null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * vedioCategoryId为空，应该返回参数错误
	 */
	@Test
	public void testGetVedios1() {
		JSONObject object = vedioAppController.getVedios(null, 1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * sortType为空，应该返回参数错误
	 */
	@Test
	public void testGetVedios2() {
		JSONObject object = vedioAppController.getVedios(2, null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 参数正常，输出应该有分类和对应的视频，时间排序
	 */
	@Test
	public void testGetVedios3() {
		JSONObject object = vedioAppController.getVedios(2, 1);// 按照时间排序

		List<VedioAppDTO> vedioList = getVediosTest(object);
		if (!CollectionUtils.isEmpty(vedioList) && vedioList.size() > 1) {
			Date date1 = vedioList.get(0).getAddTime();
			Date date2 = vedioList.get(1).getAddTime();
			boolean dateFlag = date1.getTime() > date2.getTime();// 第一个视频日期晚于第二个视频
			assertTrue("时间排序不正确", dateFlag);
		}
	}

	/**
	 * 参数正常，输出应该有分类名称和对应的视频，播放量排序
	 */
	@Test
	public void testGetVedios4() {
		JSONObject object = vedioAppController.getVedios(2, 2);// 按照时间排序

		List<VedioAppDTO> vedioList = getVediosTest(object);
		if (!CollectionUtils.isEmpty(vedioList) && vedioList.size() > 1) {
			Date date1 = vedioList.get(0).getAddTime();
			Date date2 = vedioList.get(1).getAddTime();
			boolean dateFlag = date1.getTime() > date2.getTime();// 第一个视频日期晚于第二个视频
			assertTrue("时间排序不正确", dateFlag);
		}
	}

	private List<VedioAppDTO> getVediosTest(JSONObject object) {
		assertNotNull(object);
		System.out.println(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());

		JSONObject data = (JSONObject) object.get("data");
		assertNotNull(data.get("vedioCategoryName"));
		String vedioCategoryName = (String) data.get("vedioCategoryName");
		boolean nameFlag = StringUtils.isEmpty(vedioCategoryName) ? false : true;// 分类名称是否不为空
		assertTrue("分类名称为空异常", nameFlag);

		Object vedioListObj = data.get("vedioList");// 视频列表
		assertNotNull(vedioListObj);
		List<VedioAppDTO> vedioList = (List<VedioAppDTO>) vedioListObj;
		return vedioList;
	}

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testGetVedioDetail() {
		JSONObject object = vedioAppController.getVedioDetail(null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 边界值视频不存在的
	 */
	@Test
	public void testGetVedioDetail1() {
		JSONObject object = vedioAppController.getVedioDetail(-1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());

		VedioAppDTO vedioAppDTO = (VedioAppDTO) object.get("data");
		assertNull(vedioAppDTO.getVedioName());
		assertNull(vedioAppDTO.getId());
	}

	/**
	 * 正常情况，视频存在，查出详情
	 */
	@Test
	public void testGetVedioDetail2() {
		JSONObject object = vedioAppController.getVedioDetail(3);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());

		VedioAppDTO vedioAppDTO = (VedioAppDTO) object.get("data");
		assertNotNull(vedioAppDTO.getVedioName());
		assertNotNull(vedioAppDTO.getId());
	}

	/**
	 * 入参为空，应该返回参数错误
	 */
	@Test
	public void testPlayAmont() {
		JSONObject object = vedioAppController.playAmount(null, null);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 边界值视频不存在的
	 */
	@Test
	public void testPlayAmont1() {
		JSONObject object = vedioAppController.playAmount(1, -1);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.DATA_ERROR.getCode());
	}

	/**
	 * 正常情况，视频存在,应该更新成功(游客状态、登录状态都可以)
	 */
	@Test
	public void testPlayAmont2() {
		JSONObject object = vedioAppController.playAmount(null, 3);
		assertNotNull(object);
		assertEquals(object.get("code"), ErrorAppEnums.SUCCESS.getCode());
	}

}
