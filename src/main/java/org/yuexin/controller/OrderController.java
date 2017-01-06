package org.yuexin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.dto.OrderDTO;
import org.yuexin.service.OrderService;
import org.yuexin.util.ErrorEnums;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description: TODO 付费视频订单管理
 * 
 * @author liuqin
 * 
 * @date 2017-1-5 下午3:42:21
 * 
 */
@Controller
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;

	/**
	 * 订单管理列表
	 * 
	 * @param userName
	 *            用户名
	 * @param veidoName
	 *            视频名称
	 * @param payStatus
	 *            支付状态：0-未支付；1-已支付
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	@RequestMapping(value = "/order/getOrders", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getOrders(String userName, String veidoName, Integer payStatus, String startTime, String endTime,
			@RequestParam(required = false, defaultValue = "1") Integer indexPage,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		JSONObject result = new JSONObject();
		List<OrderDTO> orderList = orderService.selectOrders(userName, veidoName, payStatus, startTime, endTime, indexPage, pageSize);
		int orderSize = orderService.countOrders(userName, veidoName, payStatus, startTime, endTime);
		result.put("errorCode", ErrorEnums.SUCCESS.getCode());
		result.put("orderList", orderList);
		result.put("orderSize", orderSize);
		return result;
	}
}
