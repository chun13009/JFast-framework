package com.jfast.system;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.jfast.database.DataBaseManager;
import com.jfast.model.BaseModel;
import com.jfast.model.IModel;
import com.jfast.model.tools.ModelDescriberManager;

public class SystemManager {

	private String name;
	private DataBaseManager dataBaseManager;
	private String dbType;
	private DataSourceTransactionManager transactionManager;
	private JtaTransactionManager jtaTxManager;
	private DataSource dataSource;
	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public IModel createModelObject(String modelName) {
		IModel model = new BaseModel();
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
	
	
}
