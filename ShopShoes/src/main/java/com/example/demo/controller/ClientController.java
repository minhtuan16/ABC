package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.BillItems;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import com.example.demo.entity.Size;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProductSizeRepo;
import com.example.demo.repository.SizeRepo;
import com.example.demo.service.ProductService;

@Controller
public class ClientController {
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductRepo productRepo;

	@Autowired
	SizeRepo sizeRepo;

	@Autowired
	ProductSizeRepo productSizeRepo;
 
	@Autowired
	ProductService productService;

	@GetMapping("")
	public String home() {
		return "client/home";
	}

//	@PostMapping("/login")
//	public String login() {
//		return "client/login";
//	}

//	@GetMapping(value = "/register")
//	public String register() {
//		return "client/register";
//	}

	@GetMapping("/polo")
	public String poloNam(Model model,
//			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "categoryID", required = false) Integer categoryID ,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		if (size == null) { 
			size = 12;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (name != null && !name.isEmpty()) {
			Page<Product> pageProduct = productRepo.searchNameP("%" + name + "%", pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else {
			Page<Product> pageProduct = productRepo.findAll(pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		return "client/polo-nam";
	}

	@GetMapping("/product")
	public String product(Model model, HttpServletRequest request,
			@RequestParam(name = "id", required = true) Integer id) {

	 	Product product = productRepo.getById(id); 
		request.setAttribute("product", product);

		 
		List<ProductSize> productSizes = productSizeRepo.findSizes(id);
		model.addAttribute("productSizes", productSizes);
		
		return "client/product";
	}
	
	@GetMapping("/cart")
	public String cart(@RequestParam(name = "id", required = true) Integer id, HttpSession session) {
		Product product = productRepo.getById(id);
		
//		ProductSize productSize = productSizeRepo.getById(id);
		
		List<ProductSize> productSizes = (List<ProductSize>) productSizeRepo.getById(product.getId());
		
		Object object = session.getAttribute("cart");
		
//		Scanner sc1 = new Scanner(System.in);
//		int n = sc1.nextInt(); 
		// neu cart rong
		if(object == null) {
			BillItems billItems = new BillItems();
			billItems.setProduct(product); // them sp vao cart
			billItems.setQuantity(1); 
			billItems.setBuyPrice(product.getPrice());
			Map<Integer, BillItems> map = new HashMap<Integer, BillItems>();
			map.put(id, billItems);
			session.setAttribute("cart", map); // set cart bang map
		} else { // neu cart da co sp
			Map<Integer, BillItems> map = (Map<Integer, BillItems>) object; // lay map ra
			BillItems billItems = map.get(id); // tim danh sach sp co trong map
			if(billItems == null) { // neu chua co sp trong map thi lay thong tin sp va sl sp = 1
				billItems = new BillItems();
				billItems.setProduct(product);
				billItems.setQuantity(1);
				billItems.setBuyPrice(product.getPrice());
				map.put(id, billItems); 
			}   
//			else { // neu co sp trong map roi thi tang so luong cua sp len +1
////				ProductSize productSize = new ProductSize(); 
//				if(billItems.getQuantity() < productSizes.get(product.getId()).getSize().getQuantity()) {
//					billItems.setQuantity(billItems.getQuantity() + 1);
//				} else {
//					billItems.setQuantity(billItems.getQuantity());
//				}
//			}
			session.setAttribute("cart", map);
		}
		return "client/cart"; 
	} 
}
