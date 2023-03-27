package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.CartService;

@Controller
@RequestMapping("/admin/cart")
public class CartController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CartService cartService;

	@GetMapping("/view")
	public String viewCarts() {
		return "/admin/cart/cartitems";
	}
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") Integer id) {
//		Product product = productRepo.findAll();
//		
//		if (product != null) {
//			CartItem item = new CartItem();
//			item.setProductId(product.get)
//			cartService.add(item.
//		}
		return "redirect:/admin/cart/view";
	}
}
