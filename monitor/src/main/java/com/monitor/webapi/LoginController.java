package com.monitor.webapi;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.entity.Role;
import com.monitor.entity.User;
import com.monitor.service.inf.ILoginService;

/**
 * @ClassName: LoginController.java
 * @Description: 用户登录controller；
 * @author wangmin
 * @date 2018年6月4日-下午5:29:34
 * @version 1.0V
 */
@RestController
public class LoginController {

	@Resource(name = "loginServiceImpl")
	private ILoginService loginService;
	
	/**
	 * 退出的时候是get请求，主要是用于退出；
	 * @return
	 * @date 2018年6月4日-下午5:55:25
	 * @author wangmin
	 */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * post登录；
     * @param map
     * @return
     * @date 2018年6月4日-下午5:55:10
     * @author wangmin
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> map){
        //	添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
        		String.valueOf(map.get("username")),
        		String.valueOf(map.get("password")));
        //	进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(usernamePasswordToken);
        return "login";
    }

    /**
     * 首页；
     * @return
     * @date 2018年6月4日-下午5:55:01
     * @author wangmin
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 登出；
     * @return
     * @date 2018年6月4日-下午5:54:38
     * @author wangmin
     */
    @RequestMapping(value = "/logout")
    public String logout(){
        return "logout";
    }

    /**
     * 错误页面展示；
     * @return
     * @date 2018年6月4日-下午5:54:19
     * @author wangmin
     */
    @RequestMapping(value = "/error",method = RequestMethod.POST)
    public String error(){
        return "error ok!";
    }

    /**
     * 数据初始化；
     * @param map
     * @return
     * @date 2018年6月4日-下午5:54:02
     * @author wangmin
     */
    @RequestMapping(value = "/addUser")
    public String addUser(@RequestBody Map<String,Object> map){
        User user = loginService.addUser(map);
        return "addUser is ok! \n" + user;
    }

    /**
     * 角色初始化；
     * @param map
     * @return
     * @date 2018年6月4日-下午5:53:48
     * @author wangmin
     */
    @RequestMapping(value = "/addRole")
    public String addRole(@RequestBody Map<String,Object> map){
        Role role = loginService.addRole(map);
        return "addRole is ok! \n" + role;
    }


    /**
     * 注解的使用
     * @return
     * @date 2018年6月4日-下午5:53:33
     * @author wangmin
     */
    @RequiresRoles("admin")
    @RequiresPermissions("create")
    @RequestMapping(value = "/create")
    public String create(){
        return "Create success!";
    }
}
