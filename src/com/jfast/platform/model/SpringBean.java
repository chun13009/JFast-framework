package com.jfast.platform.model;

import java.util.List;

import com.jfast.model.BeanModel;

/**
 * 
 * @author admin
 *
 */
public class SpringBean extends BeanModel {
	private static final long serialVersionUID = 5043913291098907382L;
	
	public static List<SpringBean> getActiveBeans() {
		// TODO:等待实现...
		return null;
	}
	public List<SpringBeanProperty> getProperties() {
		// TODO:等待实现...
		return null;
	}
	
	public boolean isLazyInit(){
		return "true".equalsIgnoreCase(getLazyInit())||"1".equalsIgnoreCase(getLazyInit());
	}
}
