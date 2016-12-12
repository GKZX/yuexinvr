package org.yuexin.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yuexin.dao.UserMapper;
import org.yuexin.model.User;


/**  
 * 创建时间：2015-1-27 下午5:15:03  
 * @author andy  
 * @version 2.2  
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User getUsers(){
		return userMapper.selectByPrimaryKey(1);
		
	}

	/**
	 * 获取app用户信息
	 * @param user app帐号密码
	 * @return app用户恓
	 */
	public User getUser(User user) {
		return userMapper.getUser(user);
	}
}
