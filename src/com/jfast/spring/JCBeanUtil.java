package com.jfast.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import com.jfast.spring.model.SpringBean;
import com.jfast.spring.model.SpringBeanProperty;

public class JCBeanUtil extends SpringBeanUtil {
	private static final long serialVersionUID = 1L;
	private static boolean _success = true;


	public static boolean isSuccessful() {
		return _success;
	}

	public void loadPlatformBeans() {
		try {
			loadPlatformMainConfig();
		} catch (Exception e) {
			//JCLogger.logError(e);
			e.printStackTrace();
			_success = false;
		}
		
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
							//JCLogger.logError(e);
							e.printStackTrace();
							_success = false;
						}
					}
				}
			}
		}
	}

	protected static AbstractBeanDefinition createBeanDefinition(SpringBean bean) {
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