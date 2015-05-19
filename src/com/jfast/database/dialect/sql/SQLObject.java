package com.jfast.database.dialect.sql;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class SQLObject {
	public static final String AND = " and ";
	public static final String OR = " or ";

	public static final String insertSQL = " insert into {0} ({1}) values ({2})";
	public static final String deleteSQL = " delete from {0} ";
	public static final String updateSQL = " update {0} set {1}";
	public static final String selectSQL = " select {0} from ( {1} ) sqlobject";
	
	public static final String countSQL = " count({0}) as {0}_count";
	public static final String sumSQL   = " sum({0}) as {0}_sum";
	public static final String avgSQL   = " avg({0}) as {0}_avg";
	public static final String maxSQL   = " max({0}) as {0}_max";
	public static final String minSQL   = " min({0}) as {0}_min";
	
	public static final String whereSQL  = " where ({0}) ";
	public static final String groupbySQL = " group by {0} ";
	public static final String havingSQL = " having {0} ";
	public static final String orderbySQL = " order by {0} ";
	
	public static final String kvSQL = "( {0} = :{1} )";
	public static final String skSQL = "sqlobject.{0}";

	private StringBuilder SQLBuilder = new StringBuilder();
	private Map<String, Object> valueListMap = new LinkedHashMap<String, Object>();
	
	private String selectContent,modelContent,where,groupby,orderby,having;
	
	private Map<String, String> attributeMap = new LinkedHashMap<String, String>();
	private Map<String, String> whereAttributeMap = new LinkedHashMap<String, String>();
	
	private OperationType operationType = OperationType.SELECT;

	public static enum OperationType {
		DELETE, INSERT, SELECT, UPDATE;
	}

	public SQLObject(String modelContent, OperationType operationType) {
		this.modelContent = modelContent;
		this.operationType = operationType;
	}

	public SQLObject putWhereAttribute(String column,Object value) {
		if (column == null) {
			return this;
		}
		if (whereAttributeMap == null) {
			whereAttributeMap = new LinkedHashMap<String, String>();
		}
		this.whereAttributeMap.put(column, column+"_whe_val");

		if (valueListMap == null) {
			valueListMap = new LinkedHashMap<String, Object>();
		}
		this.valueListMap.put(column+"_whe_val",value);
		return this;
	}
	
	public SQLObject putAttribute(String column,Object value) {
		if (column == null) {
			return this;
		}
		if (attributeMap == null) {
			attributeMap = new LinkedHashMap<String, String>();
		}
		this.attributeMap.put(column, column+"_val");
		
		if (valueListMap == null) {
			valueListMap = new LinkedHashMap<String, Object>();
		}
		this.valueListMap.put(column+"_whe_val",value);
		return this;
	}
	
	private String getWhereSQL(){
		StringBuilder sql = new StringBuilder();
		if (getWhere()!=null&&!getWhere().isEmpty()) {
			sql.append(getWhere());
			sql.append(AND);
		}
		int index=0;
		for (String key : whereAttributeMap.keySet()) {//kvSQL
			index++;
			sql.append(MessageFormat.format(kvSQL, key,whereAttributeMap.get(key)));
			if (index < (whereAttributeMap.size()-1)) {
				sql.append(AND);
			}
		}
		
		return sql.toString();
	}
	
	private String getSetSQL(){
		StringBuilder sql = new StringBuilder();
		int index=0;
		for (String key : attributeMap.keySet()) {//kvSQL
			index++;
			sql.append(MessageFormat.format(kvSQL, key,attributeMap.get(key)));
			if (index < (attributeMap.size()-1)) {
				sql.append(",");
			}
		}
		return sql.toString();
	}
	public String getSelectContent(){
		if (selectContent!=null&&!selectContent.isEmpty()) {
			return selectContent;
		}
		StringBuilder sql = new StringBuilder();
		int index=0;
		for (String key : attributeMap.keySet()) {//kvSQL
			index++;
			sql.append(MessageFormat.format(skSQL, key));
			if (index < (attributeMap.size()-1)) {
				sql.append(",");
			}
		}
		return sql.toString();
	}
	
	private String getInsertColumnSQL(){
		StringBuilder sql = new StringBuilder(" ");
		int index=0;
		for (String key : attributeMap.keySet()) {//kvSQL
			index++;
			sql.append(key);
			if (index < (attributeMap.size()-1)) {
				sql.append(",");
			}
		}
		return sql.toString();
	}
	private String getInsertValueSQL(){
		StringBuilder sql = new StringBuilder();
		int index=0;
		for (String key : attributeMap.keySet()) {//kvSQL
			index++;
			sql.append(":"+attributeMap.get(key));
			if (index < (attributeMap.size()-1)) {
				sql.append(",");
			}
		}
		return sql.toString();
	}
	
	

	public String getSQL() {
		return SQLBuilder.toString();
	}
	
	public StringBuilder BuildSQL() {
		if (this.operationType == null)
			return null;
		final int[] operationTypeSwitch = new int[OperationType.values().length];
		String where;
		switch (operationTypeSwitch[this.operationType.ordinal()]) {
			case 1://delete
				SQLBuilder.append(MessageFormat.format(deleteSQL,modelContent));
				where=getWhereSQL();
				if (where!=null&&!where.isEmpty()) {
					SQLBuilder.append(MessageFormat.format(whereSQL,where));
				}
				break;
			case 2://insert
				SQLBuilder.append(MessageFormat.format(insertSQL,modelContent,getInsertColumnSQL(),getInsertValueSQL()));
				break;
			case 3://select
				SQLBuilder.append(MessageFormat.format(selectSQL, getSelectContent(),modelContent));
				where=getWhereSQL();
				if (where!=null&&!where.isEmpty()) {
					SQLBuilder.append(MessageFormat.format(whereSQL,where));
				}
				if (getGroupby()!=null&&!getGroupby().isEmpty()) {
					SQLBuilder.append(MessageFormat.format(groupbySQL,getGroupby()));
				}
				if (getHaving()!=null&&!getHaving().isEmpty()) {
					SQLBuilder.append(MessageFormat.format(havingSQL,getHaving()));
				}
				if (getOrderby()!=null&&!getOrderby().isEmpty()) {
					SQLBuilder.append(MessageFormat.format(orderbySQL,getOrderby()));
				}
				break;
			case 4://update
				SQLBuilder.append(MessageFormat.format(updateSQL, modelContent,getSetSQL()));
				where=getWhereSQL();
				if (where!=null&&!where.isEmpty()) {
					SQLBuilder.append(MessageFormat.format(whereSQL,where));
				}
				break;
			default:
				SQLBuilder = new StringBuilder();
		}
		return SQLBuilder;
	}

	

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public Map<String, Object> getValues() {
		return valueListMap;
	}

	public void setHaving(String having) {
		this.having = having;
	}

	public String getHaving() {
		return having;
	}

	public void setSelectContent(String selectContent) {
		this.selectContent = selectContent;
	}


	public void setSQLBuilder(StringBuilder SQLBuilder) {
		this.SQLBuilder = SQLBuilder;
	}
	
	

}
