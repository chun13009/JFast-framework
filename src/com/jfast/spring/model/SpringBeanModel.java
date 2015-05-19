package com.jfast.spring.model;

import com.jfast.model.BaseModel;

public class SpringBeanModel extends BaseModel {
	private static final long serialVersionUID = 5043913291098907382L;
	public static final String TABLE_NAME = "SPRING_BEAN";

	public static final String COL_BEAN_ID = "BEAN_ID";
	public static final String COL_BEAN_CLASS = "BEAN_CLASS";
	public static final String COL_SCOPE = "SCOPE";
	public static final String COL_DEPENDS_ON = "DEPENDS_ON";
	public static final String COL_INIT_METHOD = "INIT_METHOD";
	public static final String COL_DESTROY_METHOD = "DESTROY_METHOD";
	public static final String COL_LAZY_INIT = "LAZY_INIT";
	public static final String COL_ACTIVATION = "ACTIVATION";
	public static final String COL_DESCRIPTION = "DESCRIPTION";
	public static final String COL_APP_ID = "APP_ID";

	public String getBeanId() {
		return ((String) get(COL_BEAN_ID));
	}

	public void setBeanId(String beanId) {
		set(COL_BEAN_ID, beanId);
	}

	public String getBeanClass() {
		return ((String) get(COL_BEAN_CLASS));
	}

	public void setBeanClass(String arg0) {
		set(COL_BEAN_CLASS, arg0);
	}
	
	public String getScope() {
		return ((String) get(COL_SCOPE));
	}
	
	public void setScope(String arg0) {
		set(COL_SCOPE, arg0);
	}
	
	public String getDependsOn() {
		return ((String) get(COL_DEPENDS_ON));
	}
	
	public void setDependsOn(String arg0) {
		set(COL_DEPENDS_ON, arg0);
	}

	public String getInitMethod() {
		return ((String) get(COL_INIT_METHOD));
	}

	public void setInitMethod(String arg0) {
		set(COL_INIT_METHOD, arg0);
	}

	public String getDestroyMethod() {
		return ((String) get(COL_DESTROY_METHOD));
	}

	public void setDestroyMethod(String arg0) {
		set(COL_DESTROY_METHOD, arg0);
	}

	public String getLazyInit() {
		return ((String) get(COL_LAZY_INIT));
	}

	public void setLazyInit(String arg0) {
		set(COL_LAZY_INIT, arg0);
	}

	public String getActivation() {
		return ((String) get(COL_ACTIVATION));

	}

	public void setActivation(String arg0) {
		set(COL_ACTIVATION, arg0);
	}

	public String getDescription() {
		return ((String) get(COL_DESCRIPTION));
	}

	public void setDescription(String arg0) {
		set(COL_DESCRIPTION, arg0);
	}

	public String getAppId() {
		return ((String) get(COL_APP_ID));
	}

	public void setAppId(String arg0) {
		set(COL_APP_ID, arg0);
	}
}
