package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class Cart {

	@Id
	private int productId;
	
	private String productName;
	
	private double price;
	
	private int quantity;
	
//	private String productSize;
}
