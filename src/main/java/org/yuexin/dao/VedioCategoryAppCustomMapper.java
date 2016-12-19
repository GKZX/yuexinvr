package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.dto.VedioCategoryAppDTO;

public interface VedioCategoryAppCustomMapper {
	/**
	 * 视频分类列表
	 * @param map
	 * @return
	 */
	List<VedioCategoryAppDTO> selectVedioCategorys(Map<String,Object> map);
	
}