package com.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.entity.Permission;
import com.monitor.entity.Role;
import com.monitor.entity.User;
import com.monitor.repository.RoleRepository;
import com.monitor.repository.UserRepository;
import com.monitor.service.inf.ILoginService;

/**
 * @ClassName: LoginServiceImpl.java
 * @Description: 登录操作service接口实现类；
 * @author wangmin
 * @date 2018年6月4日-下午4:59:29
 * @version 1.0V
 */
@Service(value="loginServiceImpl")
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/**
	 * 添加用户信息；
	 */
	@Override
	public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setUserName(String.valueOf(map.get("userName")));
        user.setPassword(String.valueOf(map.get("password")));
        userRepository.save(user);
        return user;
    }
	
	/**
	 * 添加角色信息；
	 */
	@Override
    public Role addRole(Map<String, Object> map) {
        User user = userRepository.findOne(Long.valueOf(map.get("userId").toString()));
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user);
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }
	
	/**
	 * 根据用户名获取用户信息；
	 */
	@Override
	public User findByName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
