package com.jfast.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模型描述对象
 * @author jason
 */
public class ModelDescriber implements Serializable{
	private static final long serialVersionUID = 254855310853038730L;
	
	private String modelName;
	private String modelType;//view|sql|table
	public static final String MODELTYPE_VIEW="view";
	public static final String MODELTYPE_SQL="sql";
	public static final String MODELTYPE_TABLE="table";
	private String modelContent;
	private String IDNames;
	
	private Map<String, ModelAttributeDescriber> attributeDescriberMap=new LinkedHashMap<String, ModelAttributeDescriber>();

	public ModelDescriber() {}
	
	
	public ModelDescriber(String modelName, String modelType, String modelContent, String iDNames) {
		this.modelName = modelName;
		this.modelType = modelType;
		this.modelContent = modelContent;
		IDNames = iDNames;
	}







	public ModelDescriber(String modelName,Map<String, ModelAttributeDescriber> attributeDescriberMap) {
		this.modelName = modelName;
		this.attributeDescriberMap = attributeDescriberMap;
	}


	public void putAttributeDescriber(String columnName, ModelAttributeDescriber attributeDescriber) {
		if (attributeDescriberMap==null) {
			attributeDescriberMap=new LinkedHashMap<String, ModelAttributeDescriber>();
		}
		this.attributeDescriberMap.put(columnName, attributeDescriber);
	}


	public ModelAttributeDescriber gettAttributeDescriber(String columnName) {
		if (attributeDescriberMap==null) {
			attributeDescriberMap=new LinkedHashMap<String, ModelAttributeDescriber>();
		}
		return this.attributeDescriberMap.get(columnName);
	}
	
	public void setAttributeDescribers(Map<String, ModelAttributeDescriber> attributeDescriberMap) {
		this.attributeDescriberMap = attributeDescriberMap;
	}

	public Map<String, ModelAttributeDescriber> getAttributeDescribers() {
		return attributeDescriberMap;
	}
	public List<String> getAttributeNames() {
		return new ArrayList<String>(attributeDescriberMap.keySet());
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getModelName() {
		return modelName;
	}


	public void setModelType(String modelType) {
		this.modelType = modelType;
	}


	public String getModelType() {
		return modelType;
	}


	public void setModelContent(String modelContent) {
		this.modelContent = modelContent;
	}


	public String getModelContent() {
		return modelContent;
	}
	
	public boolean cheekUpdate(){
		return MODELTYPE_TABLE.equalsIgnoreCase(modelType);
	}
	public boolean cheekAttribute(String attributeName){
		return attributeDescriberMap.containsKey(attributeName);
	}


	public void setIDNames(String IDNames) {
		this.IDNames = IDNames;
	}


	public String[] getIDNames() {
		if (IDNames!=null) {
			return IDNames.split("\\|");
		}
		return null;
	}
	
	
}
