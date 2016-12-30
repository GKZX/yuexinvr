package org.yuexin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuexin.dao.VedioCategoryAppCustomMapper;
import org.yuexin.dao.VedioCategoryMapper;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.VedioCategoryExample;
import org.yuexin.model.dto.VedioCategoryAppDTO;

/**
 * 
 * @Description: app视频分类
 * 
 * @author liuqin
 * 
 * @date 2016-12-19 下午2:36:13
 *  
 */
@Service
public class VedioCategoryAppService {
	@Autowired
	private VedioCategoryMapper vedioCategoryMapper;
	@Autowired
	private VedioCategoryAppCustomMapper vedioCategoryAppCustomMapper;

	/**
	 * 视频分类列表
	 * @param type 放置类型:1-置顶到首页;0-更多;
	 * @param pId 分类父级ID
	 * @return
	 */
	public List<VedioCategoryAppDTO> getVedioCategorysByType(Integer type, Integer pId) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("type", type);
		map.put("pId", pId);
		return vedioCategoryAppCustomMapper.selectVedioCategorys(map);
	}
	
	/**
	 * 根据ID查询分类信息
	 * @param id
	 * @return
	 */
	public VedioCategory getVedioCategoryById(Integer id){
		if(id == null){
			return null;
		}
		return vedioCategoryMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 统计有几个子类
	 * @param vedioCategoryPId
	 * @return
	 */
	public int countVedioCategoryByPId(Integer vedioCategoryPId){
		VedioCategoryExample example = new VedioCategoryExample();
		VedioCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andPIdEqualTo(vedioCategoryPId);
		criteria.andSysFlagEqualTo((byte) 1);
		return vedioCategoryMapper.countByExample(example);
	}
}
