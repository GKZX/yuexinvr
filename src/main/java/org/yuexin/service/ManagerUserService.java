package org.yuexin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuexin.dao.SysUserMapper;
import org.yuexin.dao.UserCustomMapper;
import org.yuexin.dao.UserMapper;
import org.yuexin.model.User;
import org.yuexin.model.UserExample;

/**
 * 
 * @Description: 用户信息管理
 * 
 * @author liuqin
 * 
 * @date 2016-12-14 下午3:53:57
 * 
 * 
 */
@Service
public class ManagerUserService {
	@Autowired
	private UserCustomMapper userCustomMapper;

	/**
	 * 所有有效用户分页查询
	 * 
	 * @return
	 */
	public List<User> selectUsersBy(Integer indexPage, Integer pageSize) {
		Integer startIndex = (indexPage - 1) * pageSize;
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put("startIndex", startIndex);
		map.put("pageSize", pageSize);
		return userCustomMapper.selectUsers(map);
	}
	
	/**
	 * 统计总用户数
	 * @return
	 */
	public int selectUserCount(){
		return userCustomMapper.countUsers(null);
	}

}
