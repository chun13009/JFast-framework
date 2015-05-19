package com.jfast.log;

/**
 * Log4jLoggerFactory.
 */
public class Log4jLoggerFactory implements ILoggerFactory {
	
	public Logger getLogger(Class<?> clazz) {
		return new Log4jLogger(clazz);
	}
	
	public Logger getLogger(String name) {
		return new Log4jLogger(name);
	}
}
