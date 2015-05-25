package com.jfast.database.dialect;

import java.util.List;
import java.util.Map;

import com.jfast.database.dialect.sql.SQLObject;
import com.jfast.model.base.ModelDescriber;

/**
 * OracleDialect.
 */
public class OracleBuildSQLDialect extends BuildSQLDialect {

	@Override
	public String BuildModelDescriber(String modelContent) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(modelContent);
		sql.append(" ) WHERE rownum < 1");
		return sql.toString();
	}

	

	private StringBuilder getPageSQL(StringBuilder queryString, Integer startIndex, Integer pageSize) {
		int endIndex = startIndex.intValue() + pageSize.intValue();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select rOraclePageSQL.*,ROWNUM as currentRow from (");
		sql.append(queryString);
		sql.append(") rOraclePageSQL where rownum <=");
		sql.append(endIndex).append(") where currentRow>");
		sql.append(startIndex);
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
