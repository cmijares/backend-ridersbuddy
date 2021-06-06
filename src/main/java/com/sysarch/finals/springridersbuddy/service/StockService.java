package com.sysarch.finals.springridersbuddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sysarch.finals.springridersbuddy.model.Stock;
import com.sysarch.finals.springridersbuddy.repository.StockRepository;

@Service
public class StockService {
	private static StockRepository stockrepository;
	
	public StockService(StockRepository stockrepository) {
		StockService.stockrepository = stockrepository;
	}
	
	
	public static void addStock(Stock stocks) {
		stockrepository.insert(stocks);
	}
	public static List<Stock> getAllStocks(){
		return stockrepository.findAll();
	}

}
