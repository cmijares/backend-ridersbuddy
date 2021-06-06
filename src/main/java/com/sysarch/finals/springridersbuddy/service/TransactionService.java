package com.sysarch.finals.springridersbuddy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sysarch.finals.springridersbuddy.model.Transactions;
import com.sysarch.finals.springridersbuddy.repository.TransactionRepository;

@Service
public class TransactionService {
	private static TransactionRepository transactionrepository;
	
	public TransactionService(TransactionRepository transactionrepository)
	{
		TransactionService.transactionrepository = transactionrepository;
	}
	
	//Add transactions
	public static void addTransaction(Transactions transactions) {
		transactionrepository.insert(transactions);
	}
	
	//getALl
	public static List<Transactions> getAllTransactions(){
		return transactionrepository.findAll();
		
	}
}
