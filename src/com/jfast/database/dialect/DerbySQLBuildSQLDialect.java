package com.jfast.database.dialect;

import java.util.List;
import java.util.Map;

import com.jfast.database.dialect.sql.SQLObject;
import com.jfast.model.base.ModelDescriber;

/**
 * 
 * @author chun
 *
 */
public class DerbySQLBuildSQLDialect extends BuildSQLDialect {

	@Override
	public String BuildModelDescriber(String modelContent) {
		return  "select * from (" + modelContent + ") where 1 = 2";
	}


	private StringBuilder getPageSQL(StringBuilder queryString, Integer startIndex, Integer pageSize) {
		StringBuilder sql = new StringBuilder();
		sql.append(queryString).append(startIndex-1).append(" ROWS FETCH NEXT ").append(pageSize).append(" ROWS ONLY");
		return sql;
	}

	@Override
	public SQLObject query(String selectContent, String modelContent, ModelDescriber modelDescriber, List<String> selectAttributeList, Map<String, Object> whereAttributeMap,
			Map<String, Object> paramMap, Integer startIndex, Integer pageSize) {
		SQLObject sql = new SQLObject(modelDescriber.getModelContent(), SQLObject.OperationType.DELETE);
		sql.setSelectContent(selectContent);
		sql.setWhere((String) paramMap.get(PARAM_WHERE));
		sql.setOrderby((String) paramMap.get(PARAM_ORDERBY));
		sql.setGroupby((String) paramMap.get(PARAM_GROUPBY));
		sql.setHaving((String) paramMap.get(PARAM_HAVING));

		for (String attributeName : selectAttributeList) {
			if (modelDescriber.cheekAttribute(attributeName)) {
				sql.putAttribute(attributeName, null);
			}
		}
		for (String attributeName : whereAttributeMap.keySet()) {
			if (modelDescriber.cheekAttribute(attributeName)) {
				sql.putWhereAttribute(attributeName, whereAttributeMap.get(attributeName));
			}
		}
		if (null != startIndex && null != pageSize) {
			sql.setSQLBuilder(getPageSQL(sql.BuildSQL(), startIndex, pageSize));
		}
		return sql;
	}
}
