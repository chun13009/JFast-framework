package com.jfast.database;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.jfast.database.dialect.BuildSQLDialect;
import com.jfast.model.IModel;
import com.jfast.model.UserSession;
import com.jfast.model.describer.ModelDescriber;

public abstract interface DataBaseManager {

	public void setDbType(String dbType);

	public void setTransactionManager(DataSourceTransactionManager transactionManager);

	public DataSourceTransactionManager getTransactionManager();

	public void setJtaTxManager(JtaTransactionManager jtaTxManager);

	public JtaTransactionManager getJtaTxManager();

	public void setBuildSQLDialect(BuildSQLDialect buildSQLDialect);

	public BuildSQLDialect getBuildSQLDialect();

	public abstract IModel insert(IModel model, UserSession userSession) throws Exception;

	public abstract List<IModel> insert(List<IModel> modelList, UserSession userSession) throws Exception;

	public abstract IModel delete(IModel model, UserSession userSession) throws Exception;

	public abstract List<IModel> delete(List<IModel> modelList, UserSession userSession) throws Exception;

	public abstract IModel update(IModel model, UserSession userSession) throws Exception;

	public abstract List<IModel> update(List<IModel> modelList, UserSession userSession) throws Exception;

	public abstract List<IModel> execute(List<IModel> modelList, UserSession userSession) throws Exception;

	public abstract List<IModel> executeJta(List<IModel> modelList, UserSession userSession) throws Exception;

	public abstract List<IModel> query(IModel model, UserSession userSession);

	public abstract List<IModel> query(IModel model, Integer startIndex, Integer pageSize, UserSession userSession);

	public abstract List<IModel> query(String sql, Map<String, Object> sqlParamMap, UserSession userSession);

	public abstract List<IModel> query(String sql, Map<String, Object> sqlParamMap, Integer startIndex, Integer pageSize, UserSession userSession);

	public abstract List<IModel> query(IModel model, Map<String, Object> paramMap, UserSession userSession);

	public abstract List<IModel> query(IModel model, Map<String, Object> paramMap, Integer startIndex, Integer pageSize, UserSession userSession);

	public abstract Object Aggregate(IModel model, String function, String attributeName, Map<String, Object> paramMap, UserSession userSession);

	public abstract ModelDescriber buildModelAttributeDescriber(ModelDescriber modelDescriber);

}
