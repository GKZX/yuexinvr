package org.test.yuexin.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.yuexin.app.controller.VedioAppController;
import org.yuexin.controller.VedioController;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.VedioAppDTO;
import org.yuexin.model.dto.VedioAppsResultDTO;
import org.yuexin.util.ErrorEnums;

import base.BaseTransactionTestCase;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class VedioControllerTest extends BaseTransactionTestCase {
	@Autowired
	private VedioController vedioController;

	/**
	 * 父类ID传入为空，默认应该查出所有一级分类
	 */
	@Test
	public void testGetVedioCategory() {
		JSONObject object = vedioController.getVedioCategory(null);
		getVedioCategorySuccess(object);
	}

	/**
	 * 父类ID传入为0，默认应该查出所有一级分类
	 */
	@Test
	public void testGetVedioCategory1() {
		JSONObject object = vedioController.getVedioCategory(0);
		getVedioCategorySuccess(object);
	}

	/**
	 * 传入父类ID，查询子类
	 */
	@Test
	public void testGetVedioCategory2() {
		Integer pId = 1;
		JSONObject object = vedioController.getVedioCategory(pId);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object vedioCategoryListObj = object.get("vedioCategoryList");// 分类列表
		assertNotNull(vedioCategoryListObj);
		List<VedioCategory> vedioCategoryList = (List<VedioCategory>) vedioCategoryListObj;
		if (!CollectionUtils.isEmpty(vedioCategoryList)) {
			for (VedioCategory vedioCategory : vedioCategoryList) {
				assertThat(vedioCategory.getpId(), equalTo(pId));// 查出的子类是对应父类下面的
			}
		}
	}

	/**
	 * 不存在的分类ID,返回分类列表为空
	 */
	@Test
	public void testGetVedioCategory3() {
		Integer pId = -1;
		JSONObject object = vedioController.getVedioCategory(pId);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object vedioCategoryListObj = object.get("vedioCategoryList");// 分类列表
		assertNull(vedioCategoryListObj);
	}

	/**
	 * testGetVedioCategory查询一级分类情况
	 * 
	 * @param object
	 */
	private void getVedioCategorySuccess(JSONObject object) {
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object vedioCategoryListObj = object.get("vedioCategoryList");// 分类列表
		assertNotNull(vedioCategoryListObj);
		List<VedioCategory> vedioCategoryList = (List<VedioCategory>) vedioCategoryListObj;
		assertThat(vedioCategoryList.size(), greaterThanOrEqualTo(1));// 一级分类肯定有
		for (VedioCategory vedioCategory : vedioCategoryList) {
			assertThat(vedioCategory.getpId(), equalTo(0));// 父类ID为0
		}
	}

	/**
	 * 视频必填字段为空，返回参数错误
	 */
	@Test
	public void testAddOrEditVedio() {
		JSONObject object = vedioController.addOrEditVedio(0, 0, 1, 0, null, 0, null, null, null, null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.PARAM_ERROR.getCode());

	}

	/**
	 * 视频必填字段已填，返回成功
	 */
	@Test
	public void testAddOrEditVedio1() {
		Integer type = 0;
		Integer vedioId = 0;
		Integer vedioCategoryPId = 1;
		Integer vedioCategoryId = 0;
		Short isFree = 0;
		Integer money = 0;
		String vedioName = "视频名称1";
		String vedioNotes = "视频简介简介";
		String vedioImgUrl = "http://adfdsl.com";
		String vedioUrl = "http://adfdsl.com";
		JSONObject object = vedioController.addOrEditVedio(type, vedioId, vedioCategoryPId, vedioCategoryId, isFree, money, vedioName,
				vedioNotes, vedioImgUrl, vedioUrl, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());
	}

	/**
	 * 视频ID不传，返回参数错误
	 */
	@Test
	public void testGetVedio() {
		JSONObject object = vedioController.getVedio(null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.PARAM_ERROR.getCode());
	}

	/**
	 * 视频ID不存在，查不出对应视频
	 */
	@Test
	public void testGetVedio1() {
		JSONObject object = vedioController.getVedio(-1, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());
		assertNull(object.get("id"));
		assertNull(object.get("vedioName"));
	}

	/**
	 * 视频ID存在，查出对应视频
	 */
	@Test
	public void testGetVedio2() {
		JSONObject object = vedioController.getVedio(-1, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());
		assertNotNull(object.get("id"));
		assertNotNull(object.get("vedioName"));
	}

	/**
	 * 没有搜索条件，默认查出所有分类的，按照10条一页
	 */
	@Test
	public void testGetVedios() {
		JSONObject object = vedioController.getVedios(null, null, null, null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		assertThat((Integer) object.get("vedioSize"), greaterThanOrEqualTo(1));
		assertThat((Integer) object.get("vedioSize"), lessThanOrEqualTo(10));
		assertNotNull(object.get("vedioList"));
	}

	/**
	 * 有搜索条件,查出来的数据应该相匹配
	 */
	@Test
	public void testGetVedios1() {
		Integer vedioCategoryId = 1;
		String searchCriteria = "战斗";
		JSONObject object = vedioController.getVedios(vedioCategoryId, searchCriteria, 1, 1, 10);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		assertThat((Integer) object.get("vedioSize"), greaterThanOrEqualTo(1));
		assertThat((Integer) object.get("vedioSize"), lessThanOrEqualTo(10));
		assertNotNull(object.get("vedioList"));
		List<Vedio> vedioList = (List<Vedio>) object.get("vedioList");
		for (Vedio vedio : vedioList) {
			assertEquals(vedio.getVedioCategoryId(), vedioCategoryId);// 该分类下的视频
			assertNotNull(vedio.getVedioName());
			assertNotNull(vedio.getVedioNotes());
			boolean searchFlag = (vedio.getVedioName().contains(searchCriteria) || vedio.getVedioNotes().contains(searchCriteria)) ? true
					: false;// 查出的视频是否含有查询的关键字
			assertTrue("不包含关键,字视频名称："+vedio.getVedioName(),searchFlag);
		}
	}
	
	/**
	 * 视频ID为空，返回参数错误
	 */
	@Test
	public void testDeleteVedios() {
		JSONObject object = vedioController.deleteVedios(null, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.PARAM_ERROR.getCode());
	}
	
	/**
	 * 视频ID不为空，返回删除成功
	 */
	@Test
	public void testDeleteVedios1() {
		Integer[] vedioIds = {1,2};
		JSONObject object = vedioController.deleteVedios(vedioIds, null);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());
	}

}
