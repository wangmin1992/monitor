package com.monitor.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName: MonitorShiroConfiguration.java
 * @Description: 过滤配置；
 * @author wangmin
 * @date 2018年6月4日-下午5:11:54
 * @version 1.0V
 */
@Configuration
public class MonitorShiroConfiguration {

	/**
	 * 将自定义的验证方式加入到容器中；
	 * @return
	 * @date 2018年6月4日-下午5:13:53
	 * @author wangmin
	 */
	@Bean
	public MonitorShiroRealm monitorShiroRealm () {
		return new MonitorShiroRealm();
	}
	
	/**
	 * 权限管理，配置主要是Realm的管理认证；
	 * @return
	 * @date 2018年6月4日-下午5:20:53
	 * @author wangmin
	 */
	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager () {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(monitorShiroRealm());
		return securityManager;
	}
	
	/**
	 * Filter工厂，设置对应的过滤条件和跳转条件；
	 * @param securityManager
	 * @return
	 * @date 2018年6月4日-下午5:23:52
	 * @author wangmin
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean (org.apache.shiro.mgt.SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<String, String>();
        //	登出
        map.put("/logout","logout");
        //	对所有用户认证
        map.put("/**","authc");
        //	配置不会被拦截的链接
        map.put("/web", "anon");
        //	登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //	首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //	错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
	}
	
	/**
	 * 加入注解的使用，不加入这个注解不生效；
	 * @param securityManager
	 * @return
	 * @date 2018年6月4日-下午5:28:21
	 * @author wangmin
	 */
	@Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(org.apache.shiro.mgt.SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
