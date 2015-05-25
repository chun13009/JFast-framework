package com.jfast.model.tools;

import java.io.Serializable;
import java.util.List;

import com.jfast.cache.CacheStore;
import com.jfast.model.SystemBaseModel;
import com.jfast.model.base.ModelDescriber;

/**
 * 模型描述对象管理器
 * @author jason
 */
public class ModelDescriberManager implements Serializable {
	private static final long serialVersionUID = 254855310853038730L;
	private static final String CACHE_SUFFIX = "_model_describer";
	//Prefix suffix 
	public static ModelDescriber getModelDescriber(String systemManagerName, String modelName) {
		return CacheStore.get(systemManagerName+CACHE_SUFFIX, modelName);
	}
	
	public static void setModelDescriber(String systemManagerName, String modelName, ModelDescriber modelDescriber) {
		CacheStore.put(systemManagerName+CACHE_SUFFIX, modelName, modelDescriber);
	}
	
	public static void buildModelDescriber(List<SystemBaseModel> baseModelList) {
		if (baseModelList != null && baseModelList.size() > 0) {
			for (SystemBaseModel systemBaseModel : baseModelList) {
				if (systemBaseModel!=null&&systemBaseModel.getSysMgr()!=null) {
					setModelDescriber(systemBaseModel.getSysMgr().getName(), systemBaseModel.getName(), createModelDescriber(systemBaseModel));
				}
			}
		}
	}

	public static ModelDescriber createModelDescriber(SystemBaseModel baseModel) {
		if (baseModel == null || baseModel.isEmpty()) {
			return null;
		}
		ModelDescriber modelDescriber = new ModelDescriber(baseModel.getName(), baseModel.getType(), baseModel.getContent(), baseModel.getIdNames());
		return baseModel.getSysMgr().buildModelAttributeDescriber(modelDescriber);
	}

}
