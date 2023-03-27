package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.CartItem;

public interface CartService {

	double total();

	int getCount();

	Collection<CartItem> getAllItems();

	void clear();

	CartItem update(int proID, int qty);

	void remove(int id);

	void add(CartItem item);


}
