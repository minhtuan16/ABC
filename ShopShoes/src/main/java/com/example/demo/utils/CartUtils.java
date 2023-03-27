package com.example.demo.utils;

import java.util.Map;

import com.example.demo.entity.Cart;

public class CartUtils {

	public static int countCart(Map<Integer, Cart> cart) {
		int q = 0;
		
		if(cart != null) { // kiem tra xem da co cart
			for(Cart c : cart.values()) { // cart.values(): la lay ra danh sach cac phan value trong Map, bo qua phan key
				q+= c.getQuantity();
			}
		}
		return q;
	}
}
