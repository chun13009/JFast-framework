package com.jfast.database;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author chun
 *
 */
public class DataSource extends DruidDataSource {
	private static final long serialVersionUID = -5169777716517652025L;
	
	//TODO 这些参数后期取配置
	// 初始连接池大小、最小空闲连接数、最大活跃连接数
	private int initialSize = 10;
	private int minIdle = 10;
	private int maxActive = 100;

	// 配置获取连接等待超时的时间
	private long maxWait = DruidDataSource.DEFAULT_MAX_WAIT;

	// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	private long timeBetweenEvictionRunsMillis = DruidDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
	// 配置连接在池中最小生存的时间
	private long minEvictableIdleTimeMillis = DruidDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	// 配置发生错误时多久重连
	private long timeBetweenConnectErrorMillis = DruidDataSource.DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS;

	/**
	 * hsqldb - "select 1 from INFORMATION_SCHEMA.SYSTEM_USERS" Oracle -
	 * "select 1 from dual" DB2 - "select 1 from sysibm.sysdummy1" mysql -
	 * "select 1"
	 */
	private String validationQuery = "select 1";
	private boolean testWhileIdle = true;
	private boolean testOnBorrow = false;
	private boolean testOnReturn = false;

	// 是否打开连接泄露自动检测
	private boolean removeAbandoned = false;
	// 连接长时间没有使用，被认为发生泄露时长
	private long removeAbandonedTimeoutMillis = 300 * 1000;
	// 发生泄露时是否需要输出 log，建议在开启连接泄露检测时开启，方便排错
	private boolean logAbandoned = false;

	// 是否缓存preparedStatement，即PSCache，对支持游标的数据库性能提升巨大，如 oracle、mysql 5.5 及以上版本
	// private boolean poolPreparedStatements = false; // oracle、mysql 5.5
	// 及以上版本建议为 true;

	// 只要maxPoolPreparedStatementPerConnectionSize>0,poolPreparedStatements就会被自动设定为true，使用oracle时可以设定此值。
	private int maxPoolPreparedStatementPerConnectionSize = -1;
	
	public DataSource() {
		super();
		initDefaultParameter();
	}

	public void initDefaultParameter() {
		this.setInitialSize(initialSize);
		this.setMinIdle(minIdle);
		this.setMaxActive(maxActive);
		this.setMaxWait(maxWait);
		this.setTimeBetweenConnectErrorMillis(timeBetweenConnectErrorMillis);
		this.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		this.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

		this.setValidationQuery(validationQuery);
		this.setTestWhileIdle(testWhileIdle);
		this.setTestOnBorrow(testOnBorrow);
		this.setTestOnReturn(testOnReturn);

		this.setRemoveAbandoned(removeAbandoned);
		this.setRemoveAbandonedTimeoutMillis(removeAbandonedTimeoutMillis);
		this.setLogAbandoned(logAbandoned);

		// 只要maxPoolPreparedStatementPerConnectionSize>0,poolPreparedStatements就会被自动设定为true，参照druid的源码
		this.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
	}

	

}
