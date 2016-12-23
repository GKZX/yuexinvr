package org.test.yuexin.app.controller;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuexin.app.controller.VedioAppController;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONObject;

public class VedioAppControllerTest extends BaseTransactionTestCase {
	@Autowired
	private VedioAppController vedioAppController;

	@Test
	public void testGetVedioCategory() {
		JSONObject object = vedioAppController.getVedioCategory(null);
		System.out.println(object);
//		assertEquals(expected, actual)
	}
}
