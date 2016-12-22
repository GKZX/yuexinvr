package org.yuexin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.AppVersionMapper;
import org.yuexin.model.AppVersion;
import org.yuexin.model.AppVersionExample;

/**
 * 
 * @Description: app迭代版本
 * 
 * @author liuqin
 * 
 * @date 2016-12-22 上午10:15:23
 * 
 */
@Service
public class AppVersionAppService {
	@Autowired
	private AppVersionMapper appVersionMapper;
	
	/**
	 * 查询最新的版本
	 * @return
	 */
	public AppVersion selectAppVersionById(){
		AppVersionExample example = new AppVersionExample();
		example.setOrderByClause("add_time desc limit 1");
		AppVersionExample.Criteria criteria = example.createCriteria();
		criteria.andSysFlagEqualTo((byte) 1);
		List<AppVersion> appVersions = appVersionMapper.selectByExampleWithBLOBs(example);
		if(!CollectionUtils.isEmpty(appVersions)){
			return appVersions.get(0);
		}
		return null;
	} 
}
