package com.jfast.database.spring.jdbc;

import org.springframework.transaction.TransactionStatus;

public interface ModelTransactionCallback<T>{
	
	public T doInTransaction(TransactionStatus status)throws Exception;

}
