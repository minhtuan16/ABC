package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.CartItem;

@Service
@SessionScope
public class CartServiceImpl implements CartService {

	Map<Integer, CartItem> maps = new HashMap<>(); // la gio hang

	
	@Override
	public void add(CartItem item) {
		CartItem cartItem = maps.get(item.getProductId());

		// kiem tra cartItem co trong gio hang hay chua
		if (cartItem == null) {
			maps.put(item.getProductId(), item);
		} else {
			cartItem.setQty(cartItem.getQty() + 1);
		}
	}

	@Override
	public void remove(int id) {
		maps.remove(id);
	}

	@Override
	public CartItem update(int proID, int qty) {
		CartItem cartItem = maps.get(proID);
		cartItem.setQty(qty);
		return cartItem;
	}

	@Override
	public void clear() {
		maps.clear();
	}

	@Override
	public Collection<CartItem> getAllItems() {
		return maps.values();
	}

	@Override
	public int getCount() {
		return maps.values().size();
	}

	@Override
	public double total() {
		return maps.values().stream().mapToDouble(item -> item.getQty() * item.getPrice()).sum();
	}
}
