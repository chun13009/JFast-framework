package com.jfast.model.base;

import java.util.List;
import java.util.Map;

import com.jfast.model.UserSession;

public interface DBKit {

	public IModel insert(UserSession userSession) throws Exception;

	public IModel delete(UserSession userSession) throws Exception;

	public IModel update(UserSession userSession) throws Exception;

	public List<IModel> execute(List<IModel> modelList, UserSession userSession) throws Exception;

	public List<IModel> query(UserSession userSession);

	public List<IModel> query(Integer startIndex, Integer pageSize, UserSession userSession);

	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, UserSession userSession);

	public List<IModel> query(String sql, Map<String, Object> sqlParamMap, Integer startIndex, Integer pageSize, UserSession userSession);

	public List<IModel> query(Map<String, Object> paramMap, UserSession userSession);

	public List<IModel> query(Map<String, Object> paramMap, Integer startIndex, Integer pageSize, UserSession userSession);

	public Integer count(String attributeName, Map<String, Object> paramMap, UserSession userSession);

	public Integer sum(String attributeName, Map<String, Object> paramMap, UserSession userSession);

	public Integer avg(String attributeName, Map<String, Object> paramMap, UserSession userSession);

	public Object max(String attributeName, Map<String, Object> paramMap, UserSession userSession);

	public Object min(String attributeName, Map<String, Object> paramMap, UserSession userSession);

}
