package com.jfast.model;

import com.jfast.model.base.Model;
import com.jfast.spring.BeanManager;
import com.jfast.system.SystemManager;


public class SystemBaseModel extends Model {
	private static final long serialVersionUID = 5043913291098907382L;
	public static final String TABLE_NAME = "SYSTEM_BASE_MODEL";

	public static final String A_ID = "ID";
	public static final String A_MODEL_NAME = "MODEL_NAME";
	public static final String A_MODEL_TYPE = "MODEL_TYPE";
	public static final String A_MODEL_CONTENT = "MODEL_CONTENT";
	public static final String A_ID_NAMES = "ID_NAMES";
	public static final String A_SYS_MGR_ID = "SYS_MGR_ID";

	public String getId() {
		return getString(A_ID);
	}
	public void setId(String id) {
		set(A_ID, id);
	}
	
	public String getName() {
		return getString(A_MODEL_NAME);
	}
	public void setName(String name) {
		set(A_MODEL_NAME, name);
	}
	
	public String getType() {
		return getString(A_MODEL_TYPE);
	}
	public void setType(String type) {
		set(A_MODEL_TYPE, type);
	}
	
	public String getContent() {
		return getString(A_MODEL_CONTENT);
	}
	public void setContent(String content) {
		set(A_MODEL_CONTENT, content);
	}
	
	public String getIdNames() {
		return getString(A_ID_NAMES);
	}
	public void setIdNames(String idNames) {
		set(A_ID_NAMES, idNames);
	}
	
	public String getSysMgrId() {
		return getString(A_SYS_MGR_ID);
	}
	public void setSysMgrId(String sysMgrId) {
		set(A_SYS_MGR_ID, sysMgrId);
	}
	
	public SystemManager getSysMgr() {
		return (SystemManager) BeanManager.getBean(getSysMgrId());
	}
	public boolean isEmpty() {
		return getName() == null ||getName().isEmpty()||getContent()==null||getContent().isEmpty()||getSysMgrId()==null||getSysMgrId().isEmpty();
	}
}
