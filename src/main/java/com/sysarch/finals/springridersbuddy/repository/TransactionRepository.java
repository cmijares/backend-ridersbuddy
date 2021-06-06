package com.sysarch.finals.springridersbuddy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sysarch.finals.springridersbuddy.model.Transactions;

public interface TransactionRepository extends MongoRepository<Transactions, String>{
	@Query("{'firstname':?0,'lastname':?1,'biketype':?2}")
	Optional<List<Transactions>> findTransaction(String firstname, String lastname, String biketype);
	
	Optional<Transactions> findByLastname(String lastname);
}
