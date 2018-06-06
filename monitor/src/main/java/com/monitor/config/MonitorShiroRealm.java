package com.monitor.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.monitor.entity.Permission;
import com.monitor.entity.Role;
import com.monitor.entity.User;
import com.monitor.service.inf.ILoginService;

/**
 * @ClassName: MonitorShiroRealm.java
 * @Description: 配置过滤条件及验证，继承AuthorizingRealm接口用户用户认证；
 * @author wangmin
 * @date 2018年6月4日-下午4:48:37
 * @version 1.0V
 */
public class MonitorShiroRealm extends AuthorizingRealm {

	@Autowired
	private ILoginService loginService;
	
	/**
	 * 角色权限和对应权限添加；
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//	获取登录用户名
		String userName = String.valueOf(principalCollection.getPrimaryPrincipal());
		User user = loginService.findByName(userName);
		
		//	添加角色和权限
		SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
		for (Role role : user.getRoles()) {
			//	添加角色
			author.addRole(role.getRoleName());
			for (Permission permission : role.getPermissions()) {
				//	添加权限
				author.addStringPermission(permission.getPermission());
			}
		}
		
		return author;
	}

	/**
	 * 用户验证；
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		/**在进行post请求时，会先验证，然后再到请求*/
		if (authenticationToken.getPrincipal() == null) {
			return null;
		}
		//	获取用户信息
		String userName = String.valueOf(authenticationToken.getPrincipal());
		
		User user = loginService.findByName(userName);
		if (user == null) {
			return null;
		} else {
			//	验证authenticationToken和simpleAuthenticationInfo的信息
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
			return authenticationInfo;
		}
	}

}
