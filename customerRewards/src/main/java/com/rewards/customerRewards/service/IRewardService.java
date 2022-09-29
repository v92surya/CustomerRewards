package com.rewards.customerRewards.service;

import com.rewards.customerRewards.model.Rewards;

/**
 * 
 * Interface to fetch rewards based on customer id
 *
 */
public interface IRewardService {
	
	public Rewards getRewardByCustomerId(long customerId);
}
