package com.sysarch.finals.springridersbuddy.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")
public class Stock {

@Id
private String id;
private String biketype;
private String qty;
private String price;
private String status;

public Stock(String biketype, String qty, String price, String status) {

	this.biketype = biketype;
	this.qty = qty;
	this.price = price;
	this.status = status;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getBiketype() {
	return biketype;
}

public void setBiketype(String biketype) {
	this.biketype = biketype;
}

public String getQty() {
	return qty;
}

public void setQty(String qty) {
	this.qty = qty;
}

public String getPrice() {
	return price;
}

public void setPrice(String price) {
	this.price = price;
}


public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}




}
