package org.yuexin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuexin.dao.OrderCustomMapper;
import org.yuexin.model.dto.OrderDTO;

@Service
public class OrderService {
	@Autowired
	private OrderCustomMapper orderCustomMapper;

	/**
	 * 根据查询条件查找付费视频订单
	 * 
	 * @param userName
	 * @param veidoName
	 * @param payStatus
	 * @param startTime
	 * @param endTime
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<OrderDTO> selectOrders(String userName, String veidoName, Integer payStatus, String startTime, String endTime,
			Integer indexPage, Integer pageSize) {
		Integer startIndex = (indexPage - 1) * pageSize;
		
		Map<String, Object> map = new HashMap<String, Object>(5);
		map.put("userName", userName);
		map.put("veidoName", veidoName);
		map.put("payStatus", payStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		return orderCustomMapper.selectOrders(map);
	}

	/**
	 * 统计订单数
	 * 
	 * @param userName
	 * @param veidoName
	 * @param payStatus
	 * @param startTime
	 * @param endTime
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public int countOrders(String userName, String veidoName, Integer payStatus, String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>(5);
		map.put("userName", userName);
		map.put("veidoName", veidoName);
		map.put("payStatus", payStatus);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return orderCustomMapper.countOrders(map);

	}
}
