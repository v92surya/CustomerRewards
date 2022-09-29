package com.rewards.customerRewards.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rewards.customerRewards.entity.Transactions;

/**
 * Repository class for transaction data
 *
 */

@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transactions, Long> {
	
	public List<Transactions> findAllByCustomerId(Long customerId);

	public List<Transactions> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp fromTs, Timestamp toTs);
}
