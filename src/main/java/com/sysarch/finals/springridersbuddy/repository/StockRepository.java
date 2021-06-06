package com.sysarch.finals.springridersbuddy.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sysarch.finals.springridersbuddy.model.Stock;

public interface StockRepository extends MongoRepository<Stock, String>{
	@Query("{'biketype': ?0}")
	Optional<Stock> findStock(String biketype);
}
