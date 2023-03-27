package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {

	List<Product> getAll(String name, String categoryName);

	int remove(Product p);

	int add(Product p);

}
