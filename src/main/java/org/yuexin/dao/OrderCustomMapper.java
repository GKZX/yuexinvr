package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.dto.OrderDTO;

public interface OrderCustomMapper {
	/**
	 * 查询付费视频订单
	 * @param map
	 * @return
	 */
	List<OrderDTO> selectOrders(Map<String, Object> map);
	
	/**
	 * 统计订单
	 * @param map
	 * @return
	 */
	int countOrders(Map<String, Object> map);
}