package org.yuexin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.SysUserMapper;
import org.yuexin.model.SysUser;
import org.yuexin.model.SysUserExample;


/**

* @Description: 管理后台用户操作

* @author liuqin

* @date 2016-12-12 上午11:15:16

*
 */
@Service
public class SysUserService {
	@Autowired
	private SysUserMapper sysuserMapper;
	
	/**
	 * 根据用户名、密码查询管理员信息
	 * @param userName
	 * @param password
	 * @return
	 */
	public SysUser selectUserByNameAndPas(String userName,String password){
		SysUserExample example = new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
		criteria.andSysUserNameEqualTo(userName);
		criteria.andSysUserPasswordEqualTo(password);
		List<SysUser> sysUserList = sysuserMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(sysUserList)){
			return sysUserList.get(0);
		}
		return null;
	}
}
