package com.jfast.test;

import java.text.MessageFormat;

import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

public class Test {

	public static void main(String[] args) {
        
        // 此时三个ClassLoader是同一个对象
        System.out.println(Thread.currentThread().getContextClassLoader()); // 当前线程的类加载器
        System.out.println(Test.class.getClassLoader()); // 当前类的类加载器
        System.out.println(ClassLoader.getSystemClassLoader()); // 系统初始的类加载器
        System.out.println(MessageFormat.format("count({0}) as {0}_count", "name")); // 系统初始的类加载器
        String location="classpath:/com/jfast/test/log4j.properties";
        System.out.println(ResourceUtils.isUrl(location));
		System.out.println(SystemPropertyUtils.resolvePlaceholders(location));
			//location = WebUtils.getRealPath(servletContext, location);
         
    }

}
