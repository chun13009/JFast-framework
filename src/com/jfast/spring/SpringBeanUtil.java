package com.jfast.spring;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringBeanUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static ApplicationContext applicationContext;
	private static GenericApplicationContext dynamicApplicationContext;
	
	private static Map<String, Object> spring_beans_map = new HashMap<String, Object>();


	public static Object getBean(String key) {
		Object obj = null;
		if (applicationContext.containsBean(key))
			obj = applicationContext.getBean(key);
		else
			obj = spring_beans_map.get(key);
		return obj;
	}

	public String[] getBeanNames() {
		return applicationContext.getBeanDefinitionNames();
	}

	public static void loadPlatformMainConfig() {
		if (applicationContext != null)return;
		//String home ="" ;//JCConstants.getHome();
		try {
			String fileName =""; //"file:" + home + File.separator + JCConstants.JC_CONFIG_DIR+ File.separator + JCConstants.JC_PLATFORM_CONFIG_DIR + File.separator+JCConstants.JC_PLATFORM_CONFIG_FILE;
			applicationContext = new FileSystemXmlApplicationContext(fileName);
			dynamicApplicationContext = new GenericApplicationContext(applicationContext);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("get bean error: ", e);
		}
	}

	public static void registerBean(String name, Object obj) {
		spring_beans_map.put(name, obj);
	}

	public ApplicationContext getSpringContext() {
		return dynamicApplicationContext;
	}

	public static Object registerBeanDefinition(String name,BeanDefinition beanDef) {
		if (dynamicApplicationContext != null) {
			//默认自动覆盖！
			//dynamicApplicationContext.setAllowBeanDefinitionOverriding(true);
			dynamicApplicationContext.registerBeanDefinition(name, beanDef);
			Object obj = dynamicApplicationContext.getBean(name);

			if (obj != null) {
				registerBean(name, obj);
				return obj;
			}
		}
		return null;
	}

	static {
		loadPlatformMainConfig();
	}
}