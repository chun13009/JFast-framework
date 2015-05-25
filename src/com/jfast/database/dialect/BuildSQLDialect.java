package com.jfast.database.dialect;

import java.util.List;
import java.util.Map;

import com.jfast.database.dialect.sql.SQLObject;
import com.jfast.model.base.ModelDescriber;

/**
 * Dialect.
 */
public abstract class BuildSQLDialect {
	public static final String PARAM_WHERE="where",PARAM_GROUPBY="groupby",PARAM_ORDERBY="orderby",PARAM_HAVING="having";
	
	public static final String DBTYPE_DB2="DB2";
	public static final String DBTYPE_DERBYSQL="DerbySQL";
	public static final String DBTYPE_MYSQL="MySQL";
	public static final String DBTYPE_ORACLE="Oracle";
	public static final String DBTYPE_POSTGRESQL="PostgreSQL";
	public static final String DBTYPE_SQLITE3="Sqlite3";
	
	public abstract String BuildModelDescriber(String modelContent);
	public abstract SQLObject query(String selectContent,String modelContent,ModelDescriber modelDescriber,List<String> selectAttributeList,Map<String, Object> whereAttributeMap, Map<String, Object> paramMap,Integer startIndex, Integer pageSize);


	
	public SQLObject insert(ModelDescriber modelDescriber, Map<String, Object> attributeMap) {
		if (modelDescriber.cheekUpdate()) {
			SQLObject sql = new SQLObject(modelDescriber.getModelContent(), SQLObject.OperationType.INSERT);
			for (String attributeName : attributeMap.keySet()) {
				if (modelDescriber.cheekAttribute(attributeName)) {
					sql.putAttribute(attributeName,attributeMap.get(attributeName));
				}
			}
			sql.BuildSQL();
			return sql;
		}
		return null;
	}

	public SQLObject update(ModelDescriber modelDescriber, Map<String, Object> attributeMap, Map<String, Object> whereAttributeMap) {
		if (modelDescriber.cheekUpdate()) {
			SQLObject sql = new SQLObject(modelDescriber.getModelContent(), SQLObject.OperationType.UPDATE);
			for (String attributeName : attributeMap.keySet()) {
				if (modelDescriber.cheekAttribute(attributeName)) {
					sql.putAttribute(attributeName,attributeMap.get(attributeName));
				}
			}
			for (String attributeName : whereAttributeMap.keySet()) {
				if (modelDescriber.cheekAttribute(attributeName)) {
					sql.putWhereAttribute(attributeName,whereAttributeMap.get(attributeName));
				}
			}
			sql.BuildSQL();
			return sql;
		}
		return null;
	}
	
	
	public SQLObject delete(ModelDescriber modelDescriber, Map<String, Object> attributeMap, Map<String, Object> whereAttributeMap) {
		if (modelDescriber.cheekUpdate()) {
			SQLObject sql = new SQLObject(modelDescriber.getModelContent(), SQLObject.OperationType.DELETE);
			for (String attributeName : attributeMap.keySet()) {
				if (modelDescriber.cheekAttribute(attributeName)) {
					sql.putAttribute(attributeName, attributeMap.get(attributeName));
				}
			}
			for (String attributeName : whereAttributeMap.keySet()) {
				if (modelDescriber.cheekAttribute(attributeName)) {
					sql.putWhereAttribute(attributeName, whereAttributeMap.get(attributeName));
				}
			}
			sql.BuildSQL();
			return sql;
		}
		return null;
	}
}






