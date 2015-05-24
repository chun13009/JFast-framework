package com.jfast.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;

import com.jfast.model.describer.ModelDescriber;
import com.jfast.system.SystemManager;


public class Model extends IModel {
	private static final long serialVersionUID = -3825009772293584389L;
	
	protected Map<String, Object> attributeMap=new LinkedHashMap<String, Object>();
	protected Map<String, IModel> childModelMap = new LinkedHashMap<String, IModel>();
	protected Map<String, Object> subObjects = new LinkedHashMap<String, Object>();
	protected IModel parentModel;
	
	protected String modelName;
	protected String modelContent;
	
	protected ModelDescriber modelDescriber;
	protected SystemManager systemManager;
	protected String status;
	
	public Model() {
	}
	
	public Model(IModel iModel) {
		if (iModel!=null) {
			this.setSystemManager(iModel.getSystemManager());
			this.setModelDescriber(iModel.getModelDescriber());
		}
	}
	
	@Override
	public void setSystemManager(SystemManager systemManager) {
		this.systemManager=systemManager;
	}
	@Override
	public SystemManager getSystemManager() {
		return this.systemManager;
	}
	@Override
	public void setModelDescriber(ModelDescriber modelDescriber) {
		this.modelDescriber=modelDescriber;
		
	}
	@Override
	public ModelDescriber getModelDescriber() {
		return this.modelDescriber;
	}
	
	@Override
	public String getModelContent() {
		if (this.modelContent==null&&modelDescriber!=null) {
			modelContent=modelDescriber.getModelContent();
		}
		return this.modelContent;
	}
	
	@Override
	public String getModelName() {
		if (this.modelName==null&&modelDescriber!=null) {
			modelName=modelDescriber.getModelName();
		}
		return this.modelName;
	}
	
	@Override
	public Object get(String attributeName) {
		return this.attributeMap.get(attributeName);
	}
	@Override
	public void set(String attributeName, Object value) {
		this.attributeMap.put(attributeName,value);
		
	}
	@Override
	public Map<String, Object> getAttributes() {
		return this.attributeMap;
	}
	@Override
	public void setAttributes(Map<String, Object> attributes) {
		if (attributes!=null&&attributes.size()>0) {
			this.attributeMap.putAll(attributes);
		}
	}
	
	@Override
	public String getStatus() {
		return this.status;
	}
	@Override
	public void setStatus(String status) {
		this.status=status;
	}
	
	@Override
	public String getString(String attributeName) {
		return get(attributeName,String.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String attributeName, Class<?> type) {
		return (T)ConvertUtils.convert(get(attributeName), type);
	}

	@Override
	public Integer getInteger(String attributeName) {
		return get(attributeName,Integer.class);
	}

	@Override
	public Long getLong(String attributeName) {
		return get(attributeName,Long.class);
	}

	@Override
	public BigInteger getBigInteger(String attributeName) {
		return get(attributeName,BigInteger.class);
	}

	@Override
	public Date getDate(String attributeName) {
		return get(attributeName,Date.class);
	}

	@Override
	public Time getTime(String attributeName) {
		return get(attributeName,Time.class);
	}

	@Override
	public Timestamp getTimestamp(String attributeName) {
		return get(attributeName,Timestamp.class);
	}

	@Override
	public Double getDouble(String attributeName) {
		return get(attributeName,Double.class);
	}

	@Override
	public Float getFloat(String attributeName) {
		return get(attributeName,Float.class);
	}

	@Override
	public Boolean getBoolean(String attributeName) {
		return get(attributeName,Boolean.class);
	}

	@Override
	public BigDecimal getBigDecimal(String attributeName) {
		return get(attributeName,BigDecimal.class);
	}

	@Override
	public Byte[] getBytes(String attributeName) {
		return get(attributeName,Byte[].class);
	}

	@Override
	public Number getNumber(String attributeName) {
		return get(attributeName,Number.class);
	}
	
	
	@Override
	public Object getChild(String childName) {
		return this.childModelMap.get(childName);
	}
	
	@Override
	public void setChild(String childName, IModel childObject) {
		 this.childModelMap.put(childName,childObject);
	}
	
	@Override
	public int getChildCount() {
		return this.childModelMap.size();
	}
	@Override
	public Map<String, IModel> getChildren() {
		return this.childModelMap;
	}
	
	
	@Override
	public Object getSubObject(String subObject) {
		return this.subObjects.get(subObject);
	}
	
	@Override
	public void setSubObject(String subObjectName, Object subObject) {
		this.subObjects.put(subObjectName,subObject);
	}
	@Override
	public void setSubObjects(Map<String, Object> subObjects) {
		if (subObjects!=null&&subObjects.size()>0) {
			this.subObjects.putAll(subObjects);
		}
	}
	
	@Override
	public Map<String, Object> getSubObjects() {
		return this.subObjects;
	}
	
	
	@Override
	public void setParent(IModel parentModel) {
		this.parentModel=parentModel;
	}
	@Override
	public IModel getParent() {
		return this.parentModel;
	}
	
	//TODO 扩展方法后期完成
	
	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toJosn() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void fromXML(String xmlModelString) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void fromJosn(String josnModelString) {
		// TODO Auto-generated method stub
		
	}
	
	
}
