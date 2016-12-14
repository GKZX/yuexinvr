package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.Vedio;

public interface VedioCustomMapper {
	/**
	 * 视频分页条件查询
	 * @param map
	 * @return
	 */
	List<Vedio> selectVedios(Map<String,Object> map);
	
	/**
	 * 条件查询视频统计
	 * @param map
	 * @return
	 */
	int countVedios(Map<String,Object> map);
}