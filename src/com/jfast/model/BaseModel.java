package com.jfast.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jfast.model.describer.ModelDescriber;
import com.jfast.system.SystemManager;


public class BaseModel extends IModel {
	private static final long serialVersionUID = -3825009772293584389L;
	
	protected Map<String, Object> attributeMap=new LinkedHashMap<String, Object>();
	//protected Map<String, IModel> childJCModelMap = new LinkedHashMap<String, IModel>();
	//protected Map<String, Object> subObjects = new LinkedHashMap<String, Object>();
	//protected IModel parentJCModel;
	protected String status;
	protected ModelDescriber modelDescriber;
	protected SystemManager systemManager;
	
	public BaseModel() {
	}
	
	public BaseModel(IModel iModel) {
		if (iModel!=null) {
			this.setSystemManager(iModel.getSystemManager());
			this.setModelDescriber(iModel.getModelDescriber());
		}
	}
	
	@Override
	public void setSystemManager(SystemManager systemManager) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SystemManager getSystemManager() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setModelDescriber(ModelDescriber modelDescriber) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ModelDescriber getModelDescriber() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object get(String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set(String attributeName, Object columnValue) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setAttributes(Map<String, Object> attributes) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object getChild(String childName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setChild(String paramString, IModel childObject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Map<String, IModel> getChildren() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setParent(IModel parentObject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public IModel getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}
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
