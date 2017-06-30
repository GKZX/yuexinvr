package org.test.yuexin.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuexin.controller.OrderController;
import org.yuexin.model.dto.OrderDTO;
import org.yuexin.util.ErrorEnums;

import base.BaseTransactionTestCase;

import com.alibaba.fastjson.JSONObject;

public class OrderControllerTest extends BaseTransactionTestCase {
	@Autowired
	private OrderController orderController;

	/**
	 * 搜索条件为空，查出所有订单
	 */
	@Test
	public void testGetOrders() {
		JSONObject object = orderController.getOrders(null, null, null, null, null, 1, 10);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object orderListObj = object.get("orderList");
		assertNotNull(orderListObj);

		Object orderSizeObj = object.get("orderSize");
		assertNotNull(orderSizeObj);
	}

	/**
	 * 搜索用户名，查出匹配的订单
	 */
	@Test
	public void testGetOrders1() {
		String userName = "132";
		JSONObject object = orderController.getOrders(userName, null, null, null, null, 1, 10);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object orderListObj = object.get("orderList");
		assertNotNull(orderListObj);

		List<OrderDTO> orderList = (List<OrderDTO>) orderListObj;
		for (OrderDTO orderDTO : orderList) {
			assertNotNull("用户名为空订单id:" + orderDTO.getId(), orderDTO.getUserName());
			assertTrue("不是这个用户的订单id:" + orderDTO.getId(), orderDTO.getUserName().contains(userName));
		}

		Object orderSizeObj = object.get("orderSize");
		assertNotNull(orderSizeObj);
	}
	
	/**
	 * 搜索视频名称，查出匹配的订单
	 */
	@Test
	public void testGetOrders2() {
		String vedioName = "滑翔";
		JSONObject object = orderController.getOrders(null, vedioName, null, null, null, 1, 10);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object orderListObj = object.get("orderList");
		assertNotNull(orderListObj);

		List<OrderDTO> orderList = (List<OrderDTO>) orderListObj;
		for (OrderDTO orderDTO : orderList) {
			assertNotNull("视频名称为空订单id:" + orderDTO.getId(), orderDTO.getVedioName());
			assertTrue("视频名称不匹配订单id:" + orderDTO.getId(), orderDTO.getVedioName().contains(vedioName));
		}

		Object orderSizeObj = object.get("orderSize");
		assertNotNull(orderSizeObj);
	}
	
	/**
	 * 未支付订单
	 */
	@Test
	public void testGetOrders3() {
		Integer payStatus = 0;
		JSONObject object = orderController.getOrders(null, null, payStatus, null, null, 1, 10);
		assertNotNull(object);
		assertEquals(object.get("errorCode"), ErrorEnums.SUCCESS.getCode());

		Object orderListObj = object.get("orderList");
		assertNotNull(orderListObj);

		List<OrderDTO> orderList = (List<OrderDTO>) orderListObj;
		for (OrderDTO orderDTO : orderList) {
			assertNotNull("支付状态为空订单id:" + orderDTO.getId(), orderDTO.getPayStatus());
			assertTrue("支付状态不匹配,订单id:" + orderDTO.getId(), orderDTO.getPayStatus() == payStatus);
		}

		Object orderSizeObj = object.get("orderSize");
		assertNotNull(orderSizeObj);
	}
}
