package com.sysarch.finals.springridersbuddy.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sysarch.finals.springridersbuddy.model.Stock;
import com.sysarch.finals.springridersbuddy.model.User;
import com.sysarch.finals.springridersbuddy.payload.request.UpdateRequest;
import com.sysarch.finals.springridersbuddy.payload.response.MessageResponse;
import com.sysarch.finals.springridersbuddy.repository.StockRepository;
import com.sysarch.finals.springridersbuddy.service.StockService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StockController {
@Autowired
	StockRepository stockrepository;

	@PostMapping("/stock")
	public Stock insertStock(@RequestBody Stock stock) {
		stock.setBiketype(stock.getBiketype().replace("\n", ""));
		Optional<Stock> s = stockrepository.findStock(stock.getBiketype());
		
		if(s.isPresent()) {
			stock.setId(s.get().getId());
		}
		
		stockrepository.save(stock);
		return stock;
	}
	@GetMapping("/stocks")
	public ResponseEntity<List<Stock>> getStock(){
		return ResponseEntity.ok(StockService.getAllStocks());
	}
	
	@PutMapping("/stocks/available/{id}")
	public Stock updateUser(@PathVariable("id") String id, String status) {
		
		Optional<Stock> stockData = stockrepository.findById(id);
		if (stockData.isPresent()) {
			Stock _stock = (Stock) stockData.get();
			_stock.setStatus("Available");

			stockrepository.save(_stock);
			return _stock;

		} else {
			return null;
		}
	}
	
	@PutMapping("/stocks/unavailable/{id}")
	public Stock updateUser1(@Valid  @PathVariable String id, String status) {
		
		Optional<Stock> stockData = stockrepository.findById(id);
		if (stockData.isPresent()) {
			Stock _stock = (Stock) stockData.get();
			_stock.setStatus("Unavailable");

			stockrepository.save(_stock);
			return _stock;

		} else {
			return null;
		}
	}
}
