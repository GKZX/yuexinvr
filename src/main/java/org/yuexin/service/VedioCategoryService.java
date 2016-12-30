package org.yuexin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuexin.dao.VedioCategoryMapper;
import org.yuexin.model.VedioCategory;
import org.yuexin.model.VedioCategoryExample;


/**

* @Description: 视频分类操作

* @author liuqin

* @date 2016-12-12 上午11:15:16

*
 */
@Service
public class VedioCategoryService {
	@Autowired
	private VedioCategoryMapper vedioCategoryMapper;
	
	/**
	 * 根据父类ID查询分类信息
	 * @param vedioCategoryPId
	 * @return
	 */
	public List<VedioCategory> getVedioCategoryByPId(Integer vedioCategoryPId){
		VedioCategoryExample example = new VedioCategoryExample();
		VedioCategoryExample.Criteria criteria = example.createCriteria();
		criteria.andPIdEqualTo(vedioCategoryPId);
		criteria.andSysFlagEqualTo((byte) 1);
		return vedioCategoryMapper.selectByExample(example);
	}
	
}
