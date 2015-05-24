package com.jfast.spring;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.jfast.cache.CacheStore;
import com.jfast.platform.model.SpringBean;
import com.jfast.platform.model.SpringBeanProperty;

public class BeanManager  implements Serializable {
	private static final long serialVersionUID = 5582241314627014240L;
	
	private static final String BEAN_CACHE_NAME = "SYS_BEAN_MANAGER";
	
	private static ApplicationContext applicationContext;
	private static GenericApplicationContext dynamicApplicationContext;
	
	
	public static Object getBean(String key) {
		Object obj = null;
		if (applicationContext.containsBean(key))
			obj = applicationContext.getBean(key);
		else
			obj = CacheStore.get(BEAN_CACHE_NAME, key);
		return obj;
	}
	
	public static void cacheBean(String name, Object obj) {
		CacheStore.put(BEAN_CACHE_NAME, name, obj);
	}
	
	
	public static Object registerBeanDefinition(String name,BeanDefinition beanDef) {
		if (dynamicApplicationContext != null) {
			//默认自动覆盖！
			//dynamicApplicationContext.setAllowBeanDefinitionOverriding(true);
			dynamicApplicationContext.registerBeanDefinition(name, beanDef);
			Object obj = dynamicApplicationContext.getBean(name);

			if (obj != null) {
				cacheBean(name, obj);
				return obj;
			}
		}
		return null;
	}
	
	public static void loadFileConfig(String configLocation) {
		if (applicationContext != null)return;
		try {
			applicationContext = new FileSystemXmlApplicationContext(configLocation);
			dynamicApplicationContext = new GenericApplicationContext(applicationContext);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("get bean error: ", e);
		}
	}
	
	public static void loadDBConfig() {
		
		List<SpringBean> beans = SpringBean.getActiveBeans();
		if ((beans != null) && (!(beans.isEmpty()))) {
			for (SpringBean bean : beans) {
				if (getBean(bean.getBeanId()) == null) {
					AbstractBeanDefinition bd = createBeanDefinition(bean);
					if (bd != null) {
						if (bean.getScope()!=null) {
							bd.setScope(bean.getScope());
						}else {
							bd.setScope("singleton");
						}
						if (bean.getLazyInit()!=null&&!bean.getLazyInit().isEmpty()) {
							bd.setLazyInit(bean.isLazyInit());
						}
						if (bean.getInitMethod()!=null&&!bean.getInitMethod().isEmpty()) {
							bd.setInitMethodName(bean.getInitMethod());
						}
						if (bean.getDestroyMethod()!=null&&!bean.getDestroyMethod().isEmpty()) {
							bd.setDestroyMethodName(bean.getDestroyMethod());
						}
						
						if (bean.getDependsOn()!=null&&!bean.getDependsOn().isEmpty()) {
							String[] tmp = bean.getDependsOn().split("\\,");
							if (tmp!=null&&tmp.length>0) {
								bd.setDependsOn(tmp);
							}
						}
						try {
							registerBeanDefinition(bean.getBeanId(), bd);
						} catch (Exception e) {
							//TODO 日志记录
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public static AbstractBeanDefinition createBeanDefinition(SpringBean bean) {
		if (bean == null) {
			return null;
		}
		BeanDefinitionBuilder bb = BeanDefinitionBuilder.rootBeanDefinition(bean.getBeanClass());
		if (bb == null)
			return null;
		List<SpringBeanProperty>  properties = bean.getProperties();
		if (properties != null) {
			Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
			for (SpringBeanProperty p : properties) {
				String type = p.getPropertyValueType();
				String name = p.getPropertyName();
				String value = p.getPropertyValue();
				if ("value".equalsIgnoreCase(type)) {
					bb.addPropertyValue(name, value);
				} else if ("ref".equalsIgnoreCase(type)) {
					bb.addPropertyReference(name, value);
				} else if (("_map".equalsIgnoreCase(type)) && (value != null)) {
					String[] tmp = value.split(":");
					if (tmp.length > 1) {
						Map<String,String> vmap =map.get(name);
						if (vmap == null)
							vmap = new HashMap<String,String>();
						if (vmap != null) {
							vmap.put(tmp[0], tmp[1]);
							map.put(name, vmap);
						}
					}
				}
			}
			if ((map != null) && (!(map.isEmpty()))) {
				for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
					bb.addPropertyValue((String) entry.getKey(),entry.getValue());
				}
			}
		}

		return bb.getBeanDefinition();
	}
	
	

}
