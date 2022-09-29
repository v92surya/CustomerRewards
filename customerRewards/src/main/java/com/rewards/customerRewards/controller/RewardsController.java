package com.rewards.customerRewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.customerRewards.entity.Customer;
import com.rewards.customerRewards.model.Rewards;
import com.rewards.customerRewards.repository.CustomerRepository;
import com.rewards.customerRewards.service.IRewardService;

/**
 * 
 * Controller class for fetching rewards based on customer ID
 *
 */

@RestController
@RequestMapping("/customer")
public class RewardsController {

	@Autowired
	IRewardService rewardsService;

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping(value = "/{customerId}/rewards", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId) {
		Customer customer = customerRepository.findByCustomerId(customerId);
		if (customer == null) {
			throw new RuntimeException("Invalid customer id");
		}
		Rewards customerRewards = rewardsService.getRewardByCustomerId(customerId);
		return new ResponseEntity<>(customerRewards, HttpStatus.OK);
	}

}
