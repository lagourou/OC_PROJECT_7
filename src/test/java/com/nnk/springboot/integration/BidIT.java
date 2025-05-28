package com.nnk.springboot.integration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.nnk.springboot.model.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@SpringBootTest
@ActiveProfiles("test")
public class BidIT {

	@Autowired
	private BidListRepository bidListRepository;

	@Test
	public void bidListTest() {

		BidList bid = new BidList();

		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(BigDecimal.valueOf(10.0));
		bid.setAsk(100.10);

		// Save
		bid = bidListRepository.save(bid);
		Assertions.assertNotNull(bid.getBidListId());
		Assertions.assertEquals(0, BigDecimal.valueOf(10.0).compareTo(bid.getBidQuantity()));

		// Update
		bid.setBidQuantity(BigDecimal.valueOf(20.0));
		bid.setAsk(101.00);
		bid = bidListRepository.save(bid);
		Assertions.assertEquals(0, BigDecimal.valueOf(20.0).compareTo(bid.getBidQuantity()));

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		Assertions.assertFalse(listResult.isEmpty());

		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		Assertions.assertFalse(bidList.isPresent());
	}
}
