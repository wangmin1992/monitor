package com.monitor.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

/**
 * @ClassName: SystemInfo.java
 * @Description: 系统信息;
 * @author wangmin
 * @date 2018年6月5日-下午2:25:44
 * @version 1.0V
 */
public class SystemInfo {

	public static void main (String[] args) {
		getIpAddress();
		getMacAddress();
		getOsProperties();
		getSystemTakesUp();
	}
	
	/**
	 * 获取本机IP地址;
	 * @return
	 * @date 2018年6月5日-下午2:36:56
	 * @author wangmin
	 */
	public static void getIpAddress () {
		String hostAddress = "";
		String hostName = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			hostAddress = address.getHostAddress();
			hostName = address.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println(hostName + ":" + hostAddress);
	}
	
	public static void getMacAddress () {
		String os = System.getProperty("os.name");
		if (os != null && os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
				String line = "";
				while ((line = reader.readLine()) != null) {
					System.out.println(line.trim());
				}
			} catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * 系统属性key值:
	 * java.version    Java 运行时环境版本  
	 * java.vendor     Java 运行时环境供应商  
	 * java.vendor.url     Java 供应商的 URL  
	 * java.home   Java 安装目录  
	 * java.vm.specification.version   Java 虚拟机规范版本  
	 * java.vm.specification.vendor    Java 虚拟机规范供应商  
	 * java.vm.specification.name  Java 虚拟机规范名称  
	 * java.vm.version     Java 虚拟机实现版本  
	 * java.vm.vendor  Java 虚拟机实现供应商  
	 * java.vm.name    Java 虚拟机实现名称  
	 * java.specification.version  Java 运行时环境规范版本  
	 * java.specification.vendor   Java 运行时环境规范供应商  
	 * java.specification.name     Java 运行时环境规范名称  
	 * java.class.version  Java 类格式版本号  
	 * java.class.path     Java 类路径  
	 * java.library.path   加载库时搜索的路径列表  
	 * java.io.tmpdir  默认的临时文件路径  
	 * java.compiler   要使用的 JIT 编译器的名称  
	 * java.ext.dirs   一个或多个扩展目录的路径  
	 * os.name     操作系统的名称  
	 * os.arch     操作系统的架构  
	 * os.version  操作系统的版本  
	 * file.separator  文件分隔符（在 UNIX 系统中是“/”）  
	 * path.separator  路径分隔符（在 UNIX 系统中是“:”）  
	 * line.separator  行分隔符（在 UNIX 系统中是“/n”）  
	 * user.name   用户的账户名称  
	 * user.home   用户的主目录  
	 * user.dir    用户的当前工作目录  
	 * 
	 * @date 2018年6月5日-下午2:54:11
	 * @author wangmin
	 */
	public static void getOsProperties () {
		Properties properties = System.getProperties();
		//	操作系统名称
		System.out.println(properties.getProperty("os.name"));
		//	操作系统架构
		System.out.println(properties.getProperty("os.arch"));
		//	操作系统版本
		System.out.println(properties.getProperty("os.version"));
		//	系统中java版本
		System.out.println(properties.getProperty("java.version"));
	}
	
	public static void getSystemTakesUp () {
		//	获取磁盘分区列表
		File[] roots = File.listRoots();     
		for (File file : roots) {     
			System.out.println(file.getPath() + " 信息如下:");     
			System.out.println("空闲未使用 = " + file.getFreeSpace()/1024/1024/1024+"G");//空闲空间     
			System.out.println("可用空间 = " + file.getUsableSpace()/1024/1024/1024+"G");//可用空间     
			System.out.println("总容量 = " + file.getTotalSpace()/1024/1024/1024+"G");//总空间   
			System.out.println("已使用的空间 = " + (file.getTotalSpace()/1024/1024/1024 - file.getUsableSpace()/1024/1024/1024) + "G");
			System.out.println(); 
		}
	}
}
