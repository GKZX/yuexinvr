package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.Vedio;
import org.yuexin.model.dto.VedioDTO;

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
	
	/**
	 * 删除视频
	 * @param map
	 * @return
	 */
	int deleteVedios(Map<String,Object> map);
	
	/**
	 * 根据ID查询视频详情
	 * @param vedioId
	 * @return
	 */
	VedioDTO selectVedioById(Integer vedioId);
}