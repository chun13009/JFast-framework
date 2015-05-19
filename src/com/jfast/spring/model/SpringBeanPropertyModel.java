package com.jfast.spring.model;

import com.jfast.model.BaseModel;

public class SpringBeanPropertyModel extends BaseModel {
	private static final long serialVersionUID = 6258765262894887974L;
	
	public static final String TABLE_NAME = "SPRING_BEAN_PROPERTY";
	
	public static final String COL_ID = "ID";
	public static final String COL_PROPERTY_NAME = "PROPERTY_NAME";
	public static final String COL_PROPERTY_VALUE_TYPE = "PROPERTY_VALUE_TYPE";
	public static final String COL_PROPERTY_VALUE = "PROPERTY_VALUE";
	public static final String COL_BEAN_ID = "BEAN_ID";

	public String getId() {
		return ((String) get(COL_ID));
	}

	public void setId(String arg0) {
		set(COL_ID, arg0);
	}

	public String getPropertyName() {
		return ((String) get(COL_PROPERTY_NAME));
	}

	public void setPropertyName(String arg0) {
		set(COL_PROPERTY_NAME, arg0);
	}

	public String getPropertyValueType() {
		return ((String) get(COL_PROPERTY_VALUE_TYPE));
	}

	public void setPropertyValueType(String arg0) {
		set(COL_PROPERTY_VALUE_TYPE, arg0);
	}

	public String getPropertyValue() {
		return ((String) get(COL_PROPERTY_VALUE));
	}

	public void setPropertyValue(String arg0) {
		set(COL_PROPERTY_VALUE, arg0);
	}

	public String getBeanId() {
		return ((String) get(COL_BEAN_ID));
	}

	public void setBeanId(String arg0) {
		set(COL_BEAN_ID, arg0);
	}
}
