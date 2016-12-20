package org.yuexin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.VedioAppCustomMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioExample;
import org.yuexin.model.dto.VedioAppDTO;
import org.yuexin.model.dto.VedioAppResultDTO;

/**
 * 
 * @Description: app 视频查询
 * 
 * @author liuqin
 * 
 * @date 2016-12-19 下午3:55:29
 * 
 */
@Service
public class VedioAppService {
	@Autowired
	private VedioMapper vedioMapper;
	@Autowired
	private VedioAppCustomMapper vedioAppCustomMapper;

	/**
	 * 视频分类显示
	 * 
	 * @param vedioCategoryId
	 * @return
	 */
	public List<VedioAppResultDTO> getVedioAppResultDTOList(Integer vedioCategoryId) {
		List<VedioAppDTO> vedioAppDTOs = selectVedioList(vedioCategoryId);
		if (!CollectionUtils.isEmpty(vedioAppDTOs)) {
			Map<Integer, Object> vedioMap = new HashMap<Integer, Object>();
			for (VedioAppDTO vedioAppDTO : vedioAppDTOs) {
				// 没有子类
				Integer key = vedioAppDTO.getVedioCategoryPId() == null ? 0 : vedioAppDTO.getVedioCategoryId();

				List<VedioAppDTO> vedioList = new ArrayList<VedioAppDTO>();
				if (vedioMap.containsKey(key)) {
					vedioList = (List<VedioAppDTO>) vedioMap.get(key);
					// 子类目前默认放2个视频、没有子类目前默认放10个视频
					if (!CollectionUtils.isEmpty(vedioList)
							&& ((key != 0 && vedioList.size() >= 2) || (key == 0 && vedioList.size() >= 10))) {
						continue;
					}
				}
				vedioList.add(vedioAppDTO);
				vedioMap.put(key, vedioList);
			}

			//组装分类视频数据list
			List<VedioAppResultDTO> vedioAppResultDTOs = new ArrayList<VedioAppResultDTO>();
			for (Map.Entry entry : vedioMap.entrySet()) {
				Integer key = (Integer) entry.getKey();
				List<VedioAppDTO> value = (List<VedioAppDTO>) entry.getValue();
				VedioAppResultDTO vedioAppResultDTO = new VedioAppResultDTO();
				vedioAppResultDTO.setId(key);// 子类ID
				vedioAppResultDTO.setName(value.get(0).getVedioCategoryName());// 子类名称
				vedioAppResultDTO.setList(value);// 对应的视频列表
				vedioAppResultDTOs.add(vedioAppResultDTO);
			}
			return vedioAppResultDTOs;
		}
		return null;
	}

	/**
	 * 根据大类查询视频
	 * 
	 * @param vedioCategoryId
	 * @return
	 */
	public List<VedioAppDTO> selectVedioList(Integer vedioCategoryId) {
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("vedioCategoryId", vedioCategoryId);
		return vedioAppCustomMapper.selectCategoryVedios(map);
	}

	/**
	 * 
	 * @param vedioCategoryId
	 * @return
	 */
	public List<Vedio> selectVediosById(Integer vedioCategoryId) {
		VedioExample example = new VedioExample();
		example.setOrderByClause("addTime");
		VedioExample.Criteria criteria = example.createCriteria();
		criteria.andVedioCategoryIdEqualTo(vedioCategoryId);
		return vedioMapper.selectByExample(example);
	}
}
