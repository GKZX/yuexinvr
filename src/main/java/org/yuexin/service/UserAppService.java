package org.yuexin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yuexin.dao.UserMapper;
import org.yuexin.model.User;
import org.yuexin.model.UserExample;
import org.yuexin.util.MD5Util;

import com.alibaba.druid.util.StringUtils;


/**  
 * 创建时间：2015-1-27 下午5:15:03  
 * @author andy  
 * @version 2.2  
 */
@Service
public class UserAppService {
	@Autowired
	private UserMapper userMapper;
	
	public User getUsers(){
		return userMapper.selectByPrimaryKey(1);
		
	}

	/**
	 * 添加app手机注册校验信息
	 *
	 * @param user 手机注册校验信息
	 * @return true：添加成功，否则失败
	 */
	public boolean addUser(User user) {
		return userMapper.insertSelective(user) == 1;
	}
	
	/**
	 * 根据用户名密码查询APP用户
	 * @param phone
	 * @param password
	 * @return
	 */
	public User getUserByPhoneAndPassword(String phone,String password) {
		if(!StringUtils.isEmpty(phone)){
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(phone);
			criteria.andPasswordEqualTo(MD5Util.replaceMD5(password));// 密码MD5加密
			 List<User> users = userMapper.selectByExample(example);
			 if(!CollectionUtils.isEmpty(users)){
				 return users.get(0);
			 }
		}
		 return null;
	}

	/**
	 * 根据用户名获取用户信息
	 *
	 * @param username 用户名(目前默认手机号码为用户名)
	 * @return app用户信息
	 */
	public User getUserByPhone(String phone) {
		if(!StringUtils.isEmpty(phone)){
			UserExample example = new UserExample();
			UserExample.Criteria criteria = example.createCriteria();
			criteria.andPhoneEqualTo(phone);
			 List<User> users = userMapper.selectByExample(example);
			 if(!CollectionUtils.isEmpty(users)){
				 return users.get(0);
			 }
		}
		 return null;
	}

	/**
	 * 根据app用户名手机号帐号状态校验用户信息是否存在
	 *
	 * @param user 用户信息
	 * @return true：用户存在，否则失败
	 */
	public boolean getUserCheck(User user) {
		return userMapper.getUserCheck(user) == 1;
	}

	/**
	 * app用户注册修改信息
	 * @param user app用户信息
	 * @return true:修改成功，否则失败
	 */
	public boolean updateUser(User user) {
		return userMapper.updateUser(user) == 1;
	}
	
	/**
	 * 修改用户密码
	 * @param user
	 * @return
	 */
	public int updateUserPassword(String userName,String password){
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andPhoneEqualTo(userName);
		
		User user = new User();
		user.setPassword(MD5Util.replaceMD5(password));// 密码MD5加密存储
		return userMapper.updateByExampleSelective(user, example);
	}
}
