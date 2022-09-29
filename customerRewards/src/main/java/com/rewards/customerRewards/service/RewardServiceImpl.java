package com.rewards.customerRewards.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.customerRewards.entity.Transactions;
import com.rewards.customerRewards.model.Rewards;
import com.rewards.customerRewards.repository.TransactionRepository;
import com.rewards.customerRewards.utils.Utils;

/**
 * Service class for calculating rewards
 *
 */

@Service
public class RewardServiceImpl implements IRewardService{

	@Autowired
	TransactionRepository repository;
	
	@Override
	public Rewards getRewardByCustomerId(long customerId) {
		
		Timestamp lastMonthTs = getDateBasedOnOffSetDays(Utils.days);
		Timestamp lastSecondMonthTs = getDateBasedOnOffSetDays(2*Utils.days);
		Timestamp lastThirdMonthTs = getDateBasedOnOffSetDays(3*Utils.days);

		List<Transactions> lastMonthTransactions = repository.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTs, Timestamp.from(Instant.now()));
		List<Transactions> lastSecondMonthTransactions = repository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTs, lastMonthTs);
		List<Transactions> lastThirdMonthTransactions = repository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTs,
						lastSecondMonthTs);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomer(customerId);
		customerRewards.setLastMonthRewardPts(lastMonthRewardPoints);
		customerRewards.setSecondLastMonthRewardPts(lastSecondMonthRewardPoints);
		customerRewards.setThirdLastMonthRewardPts(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewardsPts(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;

	}

	private Long getRewardsPerMonth(List<Transactions> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	private Long calculateRewards(Transactions txs) {
		if (txs.getTransactionAmount() > Utils.firstReward && txs.getTransactionAmount() <= Utils.secondReward) {
			return Math.round(txs.getTransactionAmount() - Utils.firstReward);
		} else if (txs.getTransactionAmount() > Utils.secondReward) {
			return Math.round(txs.getTransactionAmount() - Utils.secondReward) * 2
					+ (Utils.secondReward - Utils.firstReward);
		} else
			return 0l;

	}

	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}
