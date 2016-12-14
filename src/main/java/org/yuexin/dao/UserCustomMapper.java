package org.yuexin.dao;

import java.util.List;
import java.util.Map;

import org.yuexin.model.User;

public interface UserCustomMapper {
	/**
	 * 用户列表
	 * @param map
	 * @return
	 */
	List<User> selectUsers(Map<String,Object> map);
}