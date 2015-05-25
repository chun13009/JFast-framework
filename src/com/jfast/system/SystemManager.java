package com.jfast.system;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.jfast.database.DataBaseManager;
import com.jfast.model.SystemBaseModel;
import com.jfast.model.UserSession;
import com.jfast.model.base.IModel;
import com.jfast.model.base.Model;
import com.jfast.model.base.ModelDescriber;
import com.jfast.model.tools.ModelDescriberManager;

public class SystemManager {

	private String name;
	private DataBaseManager dataBaseManager;
	private String dbType;
	private DataSourceTransactionManager transactionManager;
	private JtaTransactionManager jtaTxManager;
	private DataSource dataSource;
	private SystemManager baseSystemManager;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public IModel createModelObject(String modelName) {
		IModel model = new Model();
		model.setSystemManager(this);
		model.setModelDescriber(ModelDescriberManager.getModelDescriber(getName(), modelName));
		return model;
	}

	public DataBaseManager getDataBaseManager() {
		return dataBaseManager;
	}

	public void setDataBaseManager(DataBaseManager dataBaseManager) {
		this.dataBaseManager = dataBaseManager;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public JtaTransactionManager getJtaTxManager() {
		return jtaTxManager;
	}

	public void setJtaTxManager(JtaTransactionManager jtaTxManager) {
		this.jtaTxManager = jtaTxManager;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public IModel insert(IModel model, UserSession userSession) throws Exception {
		return dataBaseManager.insert(model, userSession);
	}

	public List<IModel> insert(List<IModel> modelList, UserSession userSession) throws Exception {
		return dataBaseManager.insert(modelList, userSession);
	}

	public IModel delete(IModel model, UserSession userSession) throws Exception {
		return dataBaseManager.delete(model, userSession);
	}

	public List<IModel> delete(List<IModel> modelList, UserSession userSession) throws Exception {
		return dataBaseManager.delete(modelList, userSession);
	}

	public IModel update(IModel model, UserSession userSession) throws Exception {
		return dataBaseManager.update(model, userSession);
	}

	public List<IModel> update(List<IModel> modelList, UserSession userSession) throws Exception {
		return dataBaseManager.update(modelList, userSession);
	}

	public List<IModel> execute(List<IModel> modelList, UserSession userSession) throws Exception {
		Boolean isJtaTrans = Boolean.valueOf(false);
		SystemManager mgr= modelList.get(0).getSystemManager();
		String systemManagerName =mgr.getName();
		for (IModel iModel : modelList) {
			if (isJtaTrans=!systemManagerName.equalsIgnoreCase(iModel.getSystemManager().getName())) {
				 break;
			}
		}
		
		if (isJtaTrans) {
			if (mgr.getJtaTxManager()==null) {
				//TODO 初始化多数据源事务管理器
			}
			return mgr.getDataBaseManager().executeJta(modelList, userSession);
		} else {
			return mgr.getDataBaseManager().execute(modelList, userSession);
		}
	}
	
	
	
	public List<IModel> query(IModel model, UserSession userSession){
		return dataBaseManager.query(model, userSession);
	}

	public List<IModel> query(IModel model, Integer startIndex, Integer pageSize, UserSession userSession){
		return dataBaseManager.query(model, startIndex, pageSize, userSession);
	}

	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, UserSession userSession){
		return dataBaseManager.query(sql, sqlParamMap, userSession);
	}

	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, Integer startIndex, Integer pageSize, UserSession userSession){
		return dataBaseManager.query(sql, sqlParamMap, startIndex, pageSize, userSession);
	}

	public List<IModel> query(IModel model, Map<String, Object> paramMap, UserSession userSession){
		return dataBaseManager.query(model, paramMap, userSession);
	}

	public List<IModel> query(IModel model, Map<String, Object> paramMap, Integer startIndex, Integer pageSize, UserSession userSession){
		return dataBaseManager.query(model, paramMap, startIndex, pageSize, userSession);
	}

	public Object Aggregate(IModel model, String function, String attributeName, Map<String, Object> paramMap, UserSession userSession){
		return dataBaseManager.Aggregate(model, function, attributeName, paramMap, userSession);
	}


	
	
	

	public ModelDescriber buildModelAttributeDescriber(ModelDescriber modelDescriber){
		return dataBaseManager.buildModelAttributeDescriber(modelDescriber);
	}
	public ModelDescriber getModelDescriber(String modelName){
		ModelDescriber modelDescriber= ModelDescriberManager.getModelDescriber(this.getName(), modelName);
		if (modelDescriber==null&&baseSystemManager!=null) {
			SystemBaseModel model=new SystemBaseModel();
			model.setName(modelName);
			model.setSysMgrId(this.getName());
			List<IModel> modelList=model.query(null);
			if (modelList!=null&&modelList.size()>0) {
				model=new SystemBaseModel(modelList.get(0));
				if (model!=null) {
					modelDescriber=ModelDescriberManager.createModelDescriber(model);
					ModelDescriberManager.setModelDescriber(getName(), model.getName(), modelDescriber);
				}
			}
		}
		return modelDescriber;
	}

	public SystemManager getBaseSystemManager() {
		return baseSystemManager;
	}

	public void setBaseSystemManager(SystemManager baseSystemManager) {
		this.baseSystemManager = baseSystemManager;
	}
	
	
}
