package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @ClassName: App.java
 * @Description: 服务入口
 * @author wangmin
 * @date 2018年6月5日-上午9:21:57
 * @version 1.0V
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.monitor"})
@ComponentScan(basePackages = {"com.monitor"})
@EnableJpaRepositories(basePackages = {"com.monitor.repository"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App {

	public static void main (String[] args) {
		SpringApplication.run(App.class, args);
	}
}
