package org.test.yuexin.app.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yuexin.app.controller.VedioAppController;
import org.yuexin.service.VedioAppService;
import org.yuexin.service.VedioCategoryAppService;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONObject;

public class VedioAppControllerTest extends BaseTransactionTestCase {
	@Autowired
	private VedioAppController vedioAppController;

	@Test
	public void testGetVedioCategory() {
		JSONObject object = vedioAppController.getVedioCategory(1);
		System.out.println(object);
//		assertEquals(expected, actual)
	}
}
