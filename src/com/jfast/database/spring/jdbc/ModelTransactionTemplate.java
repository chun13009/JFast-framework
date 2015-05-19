package com.jfast.database.spring.jdbc;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.support.CallbackPreferringPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
//import org.springframework.transaction.support.TransactionOperations;

public class ModelTransactionTemplate extends DefaultTransactionDefinition implements InitializingBean {
	private static final long serialVersionUID = 398273720038242172L;
	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	private PlatformTransactionManager transactionManager;
	/**
	 * Construct a new TransactionTemplate for bean usage.
	 * <p>
	 * Note: The PlatformTransactionManager needs to be set before any
	 * {@code execute} calls.
	 * @see #setTransactionManager
	 */
	public ModelTransactionTemplate() {
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager.
	 * 
	 * @param transactionManager
	 *            the transaction management strategy to be used
	 */
	public ModelTransactionTemplate(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Construct a new TransactionTemplate using the given transaction manager,
	 * taking its default settings from the given transaction definition.
	 * 
	 * @param transactionManager
	 *            the transaction management strategy to be used
	 * @param transactionDefinition
	 *            the transaction definition to copy the default settings from.
	 *            Local properties can still be set to change values.
	 */
	public ModelTransactionTemplate(PlatformTransactionManager transactionManager, TransactionDefinition transactionDefinition) {
		super(transactionDefinition);
		this.transactionManager = transactionManager;
	}

	/**
	 * Set the transaction management strategy to be used.
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Return the transaction management strategy to be used.
	 */
	public PlatformTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	public void afterPropertiesSet() {
		if (this.transactionManager == null) {
			throw new IllegalArgumentException("Property 'transactionManager' is required");
		}
	}

	public <T> T execute(final ModelTransactionCallback<T> action) throws TransactionException {
		if (this.transactionManager instanceof CallbackPreferringPlatformTransactionManager) {
			return ((CallbackPreferringPlatformTransactionManager) this.transactionManager).execute(this, new TransactionCallback<T>() {
				@Override
				public T doInTransaction(TransactionStatus status) {
					try {
						return action.doInTransaction(status);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}
			});
		} else {
			TransactionStatus status = this.transactionManager.getTransaction(this);
			T result;
			try {
				result = action.doInTransaction(status);
			} catch (RuntimeException ex) {
				// Transactional code threw application exception -> rollback
				rollbackOnException(status, ex);
				throw ex;
			} catch (Error err) {
				// Transactional code threw error -> rollback
				rollbackOnException(status, err);
				throw err;
			} catch (Exception ex) {
				// Transactional code threw unexpected exception -> rollback
				rollbackOnException(status, ex);
				throw new UndeclaredThrowableException(ex, "TransactionCallback threw undeclared checked exception");
			}
			this.transactionManager.commit(status);
			return result;
		}
	}

	/**
	 * Perform a rollback, handling rollback exceptions properly.
	 * 
	 * @param status
	 *            object representing the transaction
	 * @param ex
	 *            the thrown application exception or error
	 * @throws TransactionException
	 *             in case of a rollback error
	 */
	private void rollbackOnException(TransactionStatus status, Throwable ex) throws TransactionException {
		logger.debug("Initiating transaction rollback on application exception", ex);
		try {
			this.transactionManager.rollback(status);
		} catch (TransactionSystemException ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			ex2.initApplicationException(ex);
			throw ex2;
		} catch (RuntimeException ex2) {
			logger.error("Application exception overridden by rollback exception", ex);
			throw ex2;
		} catch (Error err) {
			logger.error("Application exception overridden by rollback error", ex);
			throw err;
		}
	}
}
