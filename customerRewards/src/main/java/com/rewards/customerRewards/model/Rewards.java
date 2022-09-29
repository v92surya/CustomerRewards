package com.rewards.customerRewards.model;

/**
 * 
 * POJO class for rewards
 *
 */
public class Rewards {
	private long customer;
	private long lastMonthRewardPts;
	private long secondLastMonthRewardPts;
	private long thirdLastMonthRewardPts;
	private long totalRewardsPts;

	public long getCustomer() {
		return customer;
	}

	public void setCustomer(long customer) {
		this.customer = customer;
	}

	public long getLastMonthRewardPts() {
		return lastMonthRewardPts;
	}

	public void setLastMonthRewardPts(long lastMonthRewardPts) {
		this.lastMonthRewardPts = lastMonthRewardPts;
	}

	public long getSecondLastMonthRewardPts() {
		return secondLastMonthRewardPts;
	}

	public void setSecondLastMonthRewardPts(long secondLastMonthRewardPts) {
		this.secondLastMonthRewardPts = secondLastMonthRewardPts;
	}

	public long getThirdLastMonthRewardPts() {
		return thirdLastMonthRewardPts;
	}

	public void setThirdLastMonthRewardPts(long thirdLastMonthRewardPts) {
		this.thirdLastMonthRewardPts = thirdLastMonthRewardPts;
	}

	public long getTotalRewardsPts() {
		return totalRewardsPts;
	}

	public void setTotalRewardsPts(long totalRewardsPts) {
		this.totalRewardsPts = totalRewardsPts;
	}

	@Override
	public String toString() {
		return "Rewards [customer=" + customer + ", lastMonthRewardPts=" + lastMonthRewardPts
				+ ", secondLastMonthRewardPts=" + secondLastMonthRewardPts + ", thirdLastMonthRewardPts="
				+ thirdLastMonthRewardPts + ", totalRewardsPts=" + totalRewardsPts + "]";
	}

}
