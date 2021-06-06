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
import com.sysarch.finals.springridersbuddy.model.Transactions;
import com.sysarch.finals.springridersbuddy.model.User;
import com.sysarch.finals.springridersbuddy.payload.request.UpdateRequest;
import com.sysarch.finals.springridersbuddy.payload.response.MessageResponse;
import com.sysarch.finals.springridersbuddy.repository.StockRepository;
import com.sysarch.finals.springridersbuddy.repository.TransactionRepository;
import com.sysarch.finals.springridersbuddy.repository.UserRepository;
import com.sysarch.finals.springridersbuddy.service.TransactionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	TransactionRepository transactionrepository;
	@Autowired
	StockRepository stockrepo;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/transaction")
	public ResponseEntity insertTransaction(@RequestBody Transactions transactions) {
		
		Optional<List<Transactions>> t = transactionrepository.findTransaction(transactions.getFirstname(), transactions.getLastname(), transactions.getBiketype());
		Optional<Stock> _stock = stockrepo.findStock(transactions.getBiketype());
		Stock stock = _stock.get();

		if(t.isPresent() && t.get().size()>0 && !t.get().get(0).getStatus().equals("Returned")) {
			
			Transactions _transaction = t.get().get(0);
			int newQty = Integer.parseInt(_transaction.getBorrowerqty())+Integer.parseInt(transactions.getBorrowerqty());
			_transaction.setBorrowerqty(newQty+"");
			_transaction.setEndTime(transactions.getEndTime());
			_transaction.setStartTime(transactions.getStartTime());
			_transaction.setDuration(_transaction.getDuration());
			System.out.println("\n\n\n\n SAVED"+_transaction.getStatus());
			transactionrepository.save(_transaction);
		} else {
			System.out.println("\n\n\n\n ADDED");
			TransactionService.addTransaction(transactions);
		}
		
		stock.setQty((Integer.parseInt(stock.getQty())-Integer.parseInt(transactions.getBorrowerqty())+""));
		stockrepo.save(stock);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@GetMapping("/transactions")
	public ResponseEntity<List<Transactions>> getTransactions(){
		return ResponseEntity.ok(TransactionService.getAllTransactions());		
	}
	@GetMapping("/transaction/{lastname}")
	public ResponseEntity<Transactions> getTransactionbyid(@PathVariable("lastname") String lastname){
		
		Optional<Transactions> transactionData = transactionrepository.findByLastname(lastname);
		
		if (transactionData.isPresent()) {
				return new ResponseEntity<>(transactionData.get(), HttpStatus.OK);
			}
		else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
	}
	
	@PutMapping("/transactions/personnel/{id}")
	public Transactions updateUser(@PathVariable("id") String id, String status) {
		
		Optional<Transactions> transactionData = transactionrepository.findById(id);
		if (transactionData.isPresent()) {
			Transactions _transaction = (Transactions) transactionData.get();
			_transaction.setPersonnel("Christopher Mijares");
			_transaction.setStatus("Returned");

			transactionrepository.save(_transaction);
			
			Optional<Stock> s = stockrepo.findStock(_transaction.getBiketype());
			
			if(s.isPresent()) {
				Stock stock = s.get();
				int newQty = Integer.parseInt(_transaction.getBorrowerqty())+Integer.parseInt(stock.getQty());
				stock.setQty(newQty+"");
				stockrepo.save(stock);
			}
			
		
			
			return _transaction;

		} else {
			return null;
		}
	}
}
		
