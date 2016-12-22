package org.yuexin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.PlayRecordMapper;
import org.yuexin.dao.VedioAppCustomMapper;
import org.yuexin.dao.VedioMapper;
import org.yuexin.model.PlayRecord;
import org.yuexin.model.PlayRecordExample;
import org.yuexin.model.Vedio;
import org.yuexin.model.VedioExample;
import org.yuexin.model.dto.VedioAppDTO;
import org.yuexin.model.dto.VedioAppsResultDTO;

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
    @Autowired
    private PlayRecordMapper playRecordMapper;
    
	/**
	 * 视频分类显示
	 * 
	 * @param vedioCategoryId
	 * @return
	 */
	public List<VedioAppsResultDTO> getVedioAppsResultDTOList(Integer vedioCategoryId) {
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

			// 组装分类视频数据list
			List<VedioAppsResultDTO> VedioAppsResultDTOs = new ArrayList<VedioAppsResultDTO>();
			for (Map.Entry entry : vedioMap.entrySet()) {
				Integer key = (Integer) entry.getKey();
				List<VedioAppDTO> value = (List<VedioAppDTO>) entry.getValue();
				VedioAppsResultDTO VedioAppsResultDTO = new VedioAppsResultDTO();
				VedioAppsResultDTO.setId(key);// 子类ID
				VedioAppsResultDTO.setName(value.get(0).getVedioCategoryName());// 子类名称
				VedioAppsResultDTO.setList(value);// 对应的视频列表
				VedioAppsResultDTOs.add(VedioAppsResultDTO);
			}
			return VedioAppsResultDTOs;
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
	 * 根据ID查询
	 * @param vedioId
	 * @return
	 */
	public VedioAppDTO selectVedioAppDTOById(Integer vedioId){
		if(vedioId == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("vedioId", vedioId);
		return vedioAppCustomMapper.selectVedioById(map);
	}

	/**
	 * 单个分类下所有视频
	 * 
	 * @param vedioCategoryId
	 *            分类ID
	 * @param sortType
	 *            排序类型:1-时间倒叙;2-播放量倒叙
	 * @return
	 */
	public List<VedioAppDTO> selectVedioAppDTOsByVedioCategoryId(Integer vedioCategoryId, Integer sortType) {
		if(vedioCategoryId == null){
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("vedioCategoryId", vedioCategoryId);
		map.put("sortType", sortType);
		return vedioAppCustomMapper.selectVedios(map);
	}

	/**
	 * 更新视频播放量以及记录播放记录
	 * @param userId
	 * @param vedioId
	 */
	@Transactional
	public void updatePlayAmont(Integer userId, Integer vedioId) {
		vedioAppCustomMapper.updatePlayAmount(vedioId);// 增加播放量
		if (userId == null || userId == 0) {
			PlayRecord playRecord = new PlayRecord();
			playRecord.setUserId(userId);
			playRecord.setVedioId(vedioId);
			playRecord.setAddTime(new Date());
			playRecordMapper.insertSelective(playRecord);// 登录用户记录播放视频记录
		}
	}
}
