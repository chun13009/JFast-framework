package com.jfast.model;

import java.io.Serializable;
import java.util.Map;

import com.jfast.model.describer.ModelDescriber;
import com.jfast.system.SystemManager;

public abstract class IModel implements Serializable {
	private static final long serialVersionUID = -3825009772293584389L;

	public static final String LOADED = "LOADED";
	
	public static final String CREATE = "CREATE";
	public static final String CREATED = "CREATED";
	
	public static final String DELETE = "DELETE";
	public static final String DELETED = "DELETED";
	
	public static final String UPDATE = "UPDATE";
	public static final String UPDATED = "UPDATED";

	public abstract void setSystemManager(SystemManager systemManager);
	public abstract SystemManager getSystemManager();
	
	public abstract void setModelDescriber(ModelDescriber modelDescriber);
	public abstract ModelDescriber getModelDescriber();
	
	public abstract Object get(String attributeName);
	public abstract <T> T get(String attributeName,Class<?> type);
	public abstract void set(String attributeName, Object value);
	
	public abstract Map<String, Object> getAttributes();
	public abstract void setAttributes(Map<String, Object> attributes);
	
	public abstract Object getSubObject(String subObject);
	public abstract void setSubObject(String subObjectName, Object subObject);
	public abstract void setSubObjects(Map<String, Object> subObjects);
	public abstract String getModelName();
	public abstract String getModelContent();
	public abstract Map<String, Object> getSubObjects();
	
	

	public abstract Object getChild(String childName);

	public abstract void setChild(String paramString, IModel childObject);

	public abstract int getChildCount();

	public abstract Map<String, IModel> getChildren();

	public abstract void setParent(IModel parentObject);

	public abstract IModel getParent();

	public abstract String getStatus();

	public abstract void setStatus(String status);
	
	public abstract String getString(String attributeName);
	public abstract Integer getInteger(String attributeName);
	public abstract Long getLong(String attributeName);
	public abstract java.math.BigInteger getBigInteger(String attributeName);
	public abstract java.util.Date getDate(String attributeName);
	public abstract java.sql.Time getTime(String attributeName);
	public abstract java.sql.Timestamp getTimestamp(String attributeName);
	public abstract Double getDouble(String attributeName);
	public abstract Float getFloat(String attributeName);
	public abstract Boolean getBoolean(String attributeName);
	public abstract java.math.BigDecimal getBigDecimal(String attributeName);
	public abstract Byte[] getBytes(String attributeName);
	public abstract Number getNumber(String attributeName);
	
	public abstract String toXML();
	public abstract String toJosn();

	public abstract void fromXML(String xmlModelString);
	public abstract void fromJosn(String josnModelString);
	
	
}
