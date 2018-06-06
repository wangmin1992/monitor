package com.monitor.service.inf;

import java.util.Map;

import com.monitor.entity.Role;
import com.monitor.entity.User;

/**
 * @ClassName: ILoginService.java
 * @Description: 登录操作service接口类；
 * @author wangmin
 * @date 2018年6月4日-下午4:59:04
 * @version 1.0V
 */
public interface ILoginService {

	/**
	 * 根据用户名获取用户信息；
	 * @param userName
	 * @return
	 * @date 2018年6月4日-下午4:58:27
	 * @author wangmin
	 */
	User findByName (String userName);
	
	/**
	 * 添加用户信息；
	 * @param map
	 * @return
	 * @date 2018年6月4日-下午5:45:34
	 * @author wangmin
	 */
	User addUser(Map<String, Object> map);

	/**
	 * 添加用户角色；
	 * @param map
	 * @return
	 * @date 2018年6月4日-下午5:47:30
	 * @author wangmin
	 */
	Role addRole(Map<String, Object> map);
}
