package com.example.demo.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.utils.CartUtils; 


@RestController 
public class CartControllerAPi {

	@PostMapping("/api/cart")
	public int addToCart(@RequestBody Cart params, HttpSession session) { 
		// session la bien cua server
		Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
		
		if(cart == null) { // neu cart chua co
			cart = new HashMap<Integer, Cart>(); // tao ra cart
		}
		
		int productId = params.getProductId();
		// kiem tra cart co id sp nao chua
		// neu la true tuc la sp co trong gio hang  
		// ta chi viec tang so luong sp do len
		if (cart.containsKey(params.getProductId()) == true ) { 
			Cart c = cart.get(productId);
			c.setQuantity(c.getQuantity() + 1);
		} else { 
			// sp chua co trong gio
			// bo doi tuong params (dai dien cho thang Cart) vao trong bien cart o dong 21
			cart.put(productId, params);
		}
		
		session.setAttribute("cart", cart);
		
		return CartUtils.countCart(cart);
	}
}
