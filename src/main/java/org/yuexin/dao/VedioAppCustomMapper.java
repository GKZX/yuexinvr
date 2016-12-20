package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.dto.VedioAppDTO;

public interface VedioAppCustomMapper {
	/**
	 * 查询大类下所有视频
	 * 
	 * @param map
	 * @return
	 */
	List<VedioAppDTO> selectCategoryVedios(Map<String, Object> map);

	/**
	 * 查询单个类别下的所有视频
	 * 
	 * @param map
	 * @return
	 */
	List<VedioAppDTO> selectVedios(Map<String, Object> map);
	
	/**
	 * 更新视频播放量
	 * @param vedioId
	 * @return
	 */
	int updatePlayAmount(Integer vedioId);

}