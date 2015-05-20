package com.jfast.database.spring.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.jfast.database.DataBaseManager;
import com.jfast.database.dialect.BuildSQLDialect;
import com.jfast.database.dialect.DB2BuildSQLDialect;
import com.jfast.database.dialect.DerbySQLBuildSQLDialect;
import com.jfast.database.dialect.MySQLBuildSQLDialect;
import com.jfast.database.dialect.OracleBuildSQLDialect;
import com.jfast.database.dialect.PostgreSQLBuildSQLDialect;
import com.jfast.database.dialect.Sqlite3BuildSQLDialect;
import com.jfast.database.dialect.sql.SQLObject;
import com.jfast.model.BaseModel;
import com.jfast.model.IModel;
import com.jfast.model.UserSession;
import com.jfast.model.describer.ModelAttributeDescriber;
import com.jfast.model.describer.ModelDescriber;

public class DataBaseSpringJDBCManager extends JdbcDaoSupport implements DataBaseManager {

	private BuildSQLDialect buildSQLDialect;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private DataSourceTransactionManager transactionManager;
	private JtaTransactionManager jtaTxManager;
	private String dbType;
	
	@Override
	public void setDbType(String dbType) {
		this.dbType=dbType; 
		if (BuildSQLDialect.DBTYPE_ORACLE.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new OracleBuildSQLDialect();
		} else if(BuildSQLDialect.DBTYPE_MYSQL.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new MySQLBuildSQLDialect();
		} else if(BuildSQLDialect.DBTYPE_DB2.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new DB2BuildSQLDialect();
		} else if(BuildSQLDialect.DBTYPE_DERBYSQL.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new DerbySQLBuildSQLDialect();
		} else if(BuildSQLDialect.DBTYPE_POSTGRESQL.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new PostgreSQLBuildSQLDialect();
		} else if(BuildSQLDialect.DBTYPE_SQLITE3.equalsIgnoreCase(dbType)) {
			buildSQLDialect=new Sqlite3BuildSQLDialect();
		}
	}

	@Override
	public BuildSQLDialect getBuildSQLDialect() {
		if (buildSQLDialect == null) {
			if (BuildSQLDialect.DBTYPE_ORACLE.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new OracleBuildSQLDialect();
			} else if(BuildSQLDialect.DBTYPE_MYSQL.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new MySQLBuildSQLDialect();
			} else if(BuildSQLDialect.DBTYPE_DB2.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new DB2BuildSQLDialect();
			} else if(BuildSQLDialect.DBTYPE_DERBYSQL.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new DerbySQLBuildSQLDialect();
			} else if(BuildSQLDialect.DBTYPE_POSTGRESQL.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new PostgreSQLBuildSQLDialect();
			} else if(BuildSQLDialect.DBTYPE_SQLITE3.equalsIgnoreCase(dbType)) {
				buildSQLDialect=new Sqlite3BuildSQLDialect();
			}else {
				throw new RuntimeException("dbtype is null ,buildSQLDialect  can not init ");
			}
		}
		return buildSQLDialect;
	}

	@Override
	public void setBuildSQLDialect(BuildSQLDialect buildSQLDialect) {
		if (buildSQLDialect != null) {
			this.buildSQLDialect = buildSQLDialect;
		}
	}

	private int update(JdbcTemplate jdbcTemplate, SQLObject sqlObject) throws DataAccessException {
		return namedParameterJdbcTemplate.update(sqlObject.getSQL(), sqlObject.getValues());
	}

	@Override
	public IModel insert(IModel model, UserSession userSession) throws Exception {
		SQLObject sqlObject = buildSQLDialect.insert(model.getModelDescriber(), model.getAttributes());
		int count = update(getJdbcTemplate(), sqlObject);
		if (count > 0) {
			model.setStatus(IModel.CREATED);
		}
		return model;
	}

	@Override
	public List<IModel> insert(List<IModel> modelList, UserSession userSession) throws Exception {
		return baseExcute(modelList, userSession);
	}

	private Map<String, Object> getPKMap(IModel model) {
		ModelDescriber modelDescriber = model.getModelDescriber();
		String[] ids = modelDescriber.getIDNames();
		if (ids != null) {
			Map<String, Object> pkmap = new LinkedHashMap<String, Object>();
			String id = null;
			for (int i = 0; i < ids.length; i++) {
				id = ids[i];
				if (id != null && !id.isEmpty()) {
					pkmap.put(id, model.get(id));
				}
			}
			return pkmap;
		}
		return null;
	}

	@Override
	public IModel delete(IModel model, UserSession userSession) throws Exception {
		SQLObject sqlObject = buildSQLDialect.delete(model.getModelDescriber(), model.getAttributes(), getPKMap(model));
		int count = update(getJdbcTemplate(), sqlObject);
		if (count > 0) {
			model.setStatus(IModel.DELETED);
		}
		return model;
	}

	@Override
	public List<IModel> delete(List<IModel> modelList, UserSession userSession) throws Exception {
		return baseExcute(modelList, userSession);
	}

	@Override
	public IModel update(IModel model, UserSession userSession) throws Exception {
		SQLObject sqlObject = buildSQLDialect.update(model.getModelDescriber(), model.getAttributes(), getPKMap(model));
		int count = update(getJdbcTemplate(), sqlObject);
		if (count > 0) {
			model.setStatus(IModel.UPDATED);
		}
		return model;
	}

	@Override
	public List<IModel> update(List<IModel> modelList, UserSession userSession) throws Exception {
		return baseExcute(modelList, userSession);
	}

	private List<IModel> baseExcute(List<IModel> modelList, UserSession userSession) throws Exception {
		List<IModel> resultModelList = new ArrayList<IModel>();
		for (IModel iModel : modelList) {
			String status = iModel.getStatus();
			if (IModel.CREATE.equalsIgnoreCase(status))
				resultModelList.add(insert(iModel, userSession));
			else if (IModel.UPDATE.equalsIgnoreCase(status))
				resultModelList.add(update(iModel, userSession));
			else if (IModel.DELETE.equalsIgnoreCase(status))
				resultModelList.add(delete(iModel, userSession));
		}
		return resultModelList;
	}

	@Override
	public List<IModel> execute(final List<IModel> modelList, final UserSession userSession) throws Exception {
		if (getTransactionManager() == null) {
			return baseExcute(modelList, userSession);
		} else {
			ModelTransactionTemplate tt = new ModelTransactionTemplate(getTransactionManager());
			return tt.execute(new ModelTransactionCallback<List<IModel>>() {
				@Override
				public List<IModel> doInTransaction(TransactionStatus transactionStatus) throws Exception {
					return baseExcute(modelList, userSession);
				}
			});
		}
	}

	@Override
	public List<IModel> executeJta(final List<IModel> modelList, final UserSession userSession) throws Exception {
		if (getJtaTxManager() == null) {
			return baseExcute(modelList, userSession);
		} else {
			ModelTransactionTemplate tt = new ModelTransactionTemplate(getJtaTxManager());
			return tt.execute(new ModelTransactionCallback<List<IModel>>() {
				@Override
				public List<IModel> doInTransaction(TransactionStatus transactionStatus) throws Exception {
					return baseExcute(modelList, userSession);
				}
			});
		}
	}

	@Override
	public List<IModel> query(IModel model, UserSession userSession) {
		return query(model, null, null, userSession);
	}

	@Override
	public List<IModel> query(IModel model, Integer startIndex, Integer pageSize, UserSession userSession) {
		return query(model, null, null, null, userSession);
	}

	@Override
	public List<IModel> query(IModel model, Map<String, Object> paramMap, UserSession userSession) {
		return query(model, paramMap, null, null, userSession);
	}

	@Override
	public List<IModel> query(IModel model, Map<String, Object> paramMap, Integer startIndex, Integer pageSize, UserSession userSession) {
		SQLObject sqlObject = buildSQLDialect.query(null, model.getModelDescriber().getModelContent(), model.getModelDescriber(), model.getModelDescriber().getAttributeNames(),
				model.getAttributes(), paramMap, startIndex, pageSize);
		List<Map<String, Object>> dataList = namedParameterJdbcTemplate.queryForList(sqlObject.getSQL(), sqlObject.getValues());
		return buildQueryResult(model, dataList);
	}

	@Override
	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, UserSession userSession) {
		return query(sql, sqlParamMap, null, null, userSession);
	}

	@Override
	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, Integer startIndex, Integer pageSize, UserSession userSession) {
		SQLObject sqlObject = buildSQLDialect.query("*", sql, null, null, sqlParamMap, null, startIndex, pageSize);
		List<Map<String, Object>> dataList = namedParameterJdbcTemplate.queryForList(sqlObject.getSQL(), sqlObject.getValues());
		return buildQueryResult(null, dataList);
	}

	@Override
	public Object Aggregate(IModel model, String function, String attributeName, Map<String, Object> paramMap, UserSession userSession) {
		SQLObject sqlObject = buildSQLDialect.query(MessageFormat.format(function, attributeName), model.getModelDescriber().getModelContent(), model.getModelDescriber(), null,
				model.getAttributes(), paramMap, null, null);
		List<Map<String, Object>> dataList = namedParameterJdbcTemplate.queryForList(sqlObject.getSQL(), sqlObject.getValues());
		return dataList.get(0).values().iterator().next();
	}

	@Override
	public ModelDescriber buildModelAttributeDescriber(ModelDescriber modelDescriber) {
		Statement stm = null;
		ResultSet rs = null;
		try {
			String sql = buildSQLDialect.BuildModelDescriber(modelDescriber.getModelContent());
			stm = getJdbcTemplate().getDataSource().getConnection().createStatement();
			rs = stm.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String name = rsmd.getColumnName(i);
				String label = rsmd.getColumnLabel(i);
				int type = rsmd.getColumnType(i);
				String typeName = rsmd.getColumnTypeName(i);
				int precision = rsmd.getPrecision(i);
				int displaySize = rsmd.getColumnDisplaySize(i);
				String className = rsmd.getColumnClassName(i);
				Class<?> javaType;
				if ("java.lang.String".equals(className)) {
					// varchar, char, enum, set, text, tinytext, mediumtext,longtext
					javaType = java.lang.String.class;
				} else if ("java.lang.Integer".equals(className)) {
					// int, integer, tinyint, smallint, mediumint
					javaType = java.lang.Integer.class;
				} else if ("java.lang.Long".equals(className)) {
					// bigint
					javaType = java.lang.Long.class;
				} else if ("java.sql.Date".equals(className)) {
					// date, year
					javaType = java.sql.Date.class;
				} else if ("java.lang.Double".equals(className)) {
					// real, double
					javaType = java.lang.Double.class;
				} else if ("java.lang.Float".equals(className)) {
					// float
					javaType = java.lang.Float.class;
				} else if ("java.lang.Boolean".equals(className)) {
					// bit
					javaType = java.lang.Boolean.class;
				} else if ("java.sql.Time".equals(className)) {
					// time
					javaType = java.sql.Time.class;
				} else if ("java.sql.Timestamp".equals(className)) {
					// timestamp, datetime
					javaType = java.sql.Timestamp.class;
				} else if ("java.math.BigDecimal".equals(className)) {
					// decimal, numeric
					javaType = java.math.BigDecimal.class;
				} else if ("[B".equals(className)) {
					// binary, varbinary, tinyblob, blob, mediumblob, longblob qjd project: print_info.content varbinary(61800);
					javaType = byte[].class;
				} else {
					if (type == Types.BLOB) {
						javaType = byte[].class;
					} else if (type == Types.CLOB || type == Types.NCLOB) {
						javaType = String.class;
					} else {
						javaType = String.class;
					}
				}
				ModelAttributeDescriber modelAttributeDescriber = new ModelAttributeDescriber(name, label, typeName, type, precision, displaySize, className, javaType);
				modelDescriber.putAttributeDescriber(name, modelAttributeDescriber);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return modelDescriber;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setJtaTxManager(JtaTransactionManager jtaTxManager) {
		this.jtaTxManager = jtaTxManager;
	}

	public JtaTransactionManager getJtaTxManager() {
		return jtaTxManager;
	}

	private List<IModel> buildQueryResult(IModel baseModel, List<Map<String, Object>> list) {
		List<IModel> retList = new ArrayList<IModel>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				IModel jto = createBaseModel(baseModel);
				jto.setStatus(IModel.LOADED);
				Map<String, Object> properties = list.get(i);
				jto.setAttributes(properties);
				retList.add(jto);
			}
		}
		return retList;
	}

	private IModel createBaseModel(IModel baseModel) {
		IModel retObject = new BaseModel(baseModel);
		return retObject;
	}

	@Override
	public void setTransactionManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager=transactionManager;
	}

}
