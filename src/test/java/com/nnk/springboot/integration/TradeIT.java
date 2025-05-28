package com.nnk.springboot.integration;

import com.nnk.springboot.model.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class TradeIT {

	@Autowired
	private TradeRepository tradeRepository;

	@Test
	public void tradeTest() {
		Trade trade = new Trade();

		trade.setAccount("Trade Account");
		trade.setType("Type");
		trade.setStatus("EXECUTED");
		trade.setBuyQuantity(1000.0);
		trade.setSellQuantity(500.0);
		trade.setBuyPrice(100.5);
		trade.setSellPrice(99.0);
		trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
		trade.setCreationDate(new Timestamp(System.currentTimeMillis()));

		// Save
		trade = tradeRepository.save(trade);
		Assertions.assertNotNull(trade.getTradeId());
		Assertions.assertEquals("Trade Account", trade.getAccount());

		// Update
		trade.setAccount("Trade Account Update");
		trade.setBuyQuantity(1200.0);
		trade = tradeRepository.save(trade);
		Assertions.assertEquals("Trade Account Update", trade.getAccount());

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		Assertions.assertFalse(listResult.isEmpty());

		// Delete
		Integer id = trade.getTradeId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		Assertions.assertFalse(tradeList.isPresent());
	}
}
