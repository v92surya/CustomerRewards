package com.rewards.customerRewards.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rewards.customerRewards.entity.Customer;

/**
 * Repository class for customer data
 *
 */

@Repository
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	public Customer findByCustomerId(Long customerId);
	
}
