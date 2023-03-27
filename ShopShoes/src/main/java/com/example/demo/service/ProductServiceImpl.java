package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductService productService;
	

	public static List<Product> ls = new ArrayList<Product>();
	
	@Override
	public int add(Product p) {
		return 0;
	}
	
	@Override
	public int remove(Product p) {
		return 0;
	}

	@Override
	public List<Product> getAll(String name, String categoryName) {
//		List<Product> listProducts = productService.sear
		return null;
	}
	
	
}
