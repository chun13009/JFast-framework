package com.jfast.model.tools;

import java.io.Serializable;

import com.jfast.model.describer.ModelDescriber;
import com.jfast.system.SystemManager;
/**
 * 模型描述对象管理器
 * @author jason
 */
public class ModelDescriberManager implements Serializable{
	private static final long serialVersionUID = 254855310853038730L;
	/**
	 * 
	 * @param systemManagerName
	 * @param modelName
	 * @return
	 */
	public static ModelDescriber getModelDescriber(String systemManagerName,String modelName){
		//TODO 实现从缓存管理器中取得模型描述对象
		return null;
	}
	public static void setModelDescriber(String systemManagerName,String modelName){
		//TODO 实现将模型描述对象放入缓存管理器中
	}
	
	public static void buildModelDescriber(SystemManager mgr){
		//TODO 根据系统管理器加载出对应的模型描述对象并存放到存管理器中
	}
}
