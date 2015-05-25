package com.jfast.model.base;

import java.util.List;
import java.util.Map;

import com.jfast.model.UserSession;
import com.jfast.system.SystemManager;

public interface IModel {
	public static final String LOADED = "LOADED";
	
	public static final String CREATE = "CREATE";
	public static final String CREATED = "CREATED";
	
	public static final String DELETE = "DELETE";
	public static final String DELETED = "DELETED";
	
	public static final String UPDATE = "UPDATE";
	public static final String UPDATED = "UPDATED";

	public void setSystemManager(SystemManager systemManager);
	public SystemManager getSystemManager();
	
	public void setModelDescriber(ModelDescriber modelDescriber);
	public ModelDescriber getModelDescriber();
	
	public Object get(String attributeName);
	public <T> T get(String attributeName,Class<?> type);
	public void set(String attributeName, Object value);
	
	public Map<String, Object> getAttributes();
	public void setAttributes(Map<String, Object> attributes);
	
	public Object getSubObject(String subObject);
	public void setSubObject(String subObjectName, Object subObject);
	public void setSubObjects(Map<String, Object> subObjects);
	public String getModelName();
	public String getModelContent();
	public Map<String, Object> getSubObjects();
	
	
	public IModel insert(UserSession userSession) throws Exception;
	public IModel delete(UserSession userSession) throws Exception;
	public IModel update(UserSession userSession) throws Exception;
	public List<IModel> execute(List<IModel> modelList, UserSession userSession) throws Exception;
	public List<IModel> query(UserSession userSession);
	public List<IModel> query(Integer startIndex, Integer pageSize, UserSession userSession);
	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, UserSession userSession);
	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, Integer startIndex, Integer pageSize, UserSession userSession);
	public List<IModel> query(Map<String, Object> paramMap, UserSession userSession);
	public List<IModel> query(Map<String, Object> paramMap, Integer startIndex, Integer pageSize, UserSession userSession);
	public Integer count(String attributeName, Map<String, Object> paramMap, UserSession userSession);
	public Integer sum(String attributeName, Map<String, Object> paramMap, UserSession userSession);
	public Integer avg(String attributeName, Map<String, Object> paramMap, UserSession userSession);
	public Object max(String attributeName, Map<String, Object> paramMap, UserSession userSession);
	public Object min(String attributeName, Map<String, Object> paramMap, UserSession userSession);
	
	
	

	public Object getChild(String childName);

	public void setChild(String paramString, IModel childObject);

	public int getChildCount();

	public Map<String, IModel> getChildren();

	public void setParent(IModel parentObject);

	public IModel getParent();

	public String getStatus();

	public void setStatus(String status);
	
	public String getString(String attributeName);
	public Integer getInteger(String attributeName);
	public Long getLong(String attributeName);
	public java.math.BigInteger getBigInteger(String attributeName);
	public java.util.Date getDate(String attributeName);
	public java.sql.Time getTime(String attributeName);
	public java.sql.Timestamp getTimestamp(String attributeName);
	public Double getDouble(String attributeName);
	public Float getFloat(String attributeName);
	public Boolean getBoolean(String attributeName);
	public java.math.BigDecimal getBigDecimal(String attributeName);
	public Byte[] getBytes(String attributeName);
	public Number getNumber(String attributeName);
	
	public String toXML();
	public String toJosn();

	public void fromXML(String xmlModelString);
	public void fromJosn(String josnModelString);
	
	
}
