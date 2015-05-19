package com.jfast.model.describer;

import java.io.Serializable;
/**
 * 模型属性描述对象
 * @author admin
 *
 */
public class ModelAttributeDescriber implements Serializable {
	private static final long serialVersionUID = 7966136852835601886L;
	
	private String name;
	private String label;
	private String typeName;
	private int type;
	private int precision;
	private int displaySize;
	private String className;
	private String dispalyName;
	private boolean primaryKey = false;
	
	
	private Class<?> javaType = java.lang.String.class;
	private Object defaultValue;
	private String constraint = "";

	public ModelAttributeDescriber() {
	}
	
	

	public ModelAttributeDescriber(String name, String label, String typeName, int type, int precision, int displaySize, String className, Class<?> javaType) {
		this.name = name;
		this.label = label;
		this.typeName = typeName;
		this.type = type;
		this.precision = precision;
		this.displaySize = displaySize;
		this.className = className;
		this.javaType = javaType;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDispalyName() {
		return dispalyName;
	}

	public void setDispalyName(String dispalyName) {
		this.dispalyName = dispalyName;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}
	
	
}
