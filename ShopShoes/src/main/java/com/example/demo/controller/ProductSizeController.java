package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Bill;
import com.example.demo.entity.BillItems;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import com.example.demo.entity.Size;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.ProductSizeRepo;
import com.example.demo.repository.SizeRepo;

@Controller
@RequestMapping("/admin/product_size")
public class ProductSizeController {
	private static Logger logger = LoggerFactory.getLogger(ProductSizeController.class);
	
	@Autowired
	ProductSizeRepo productSizeRepo;

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	SizeRepo sizeRepo;
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		return "admin/product_size/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute ProductSize productSize) {
		productSizeRepo.save(productSize);
		
		return "redirect:/admin/product_size/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		ProductSize ps = productSizeRepo.getById(id);
		
		model.addAttribute("ps", ps);
		
		return "admin/product_size/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute ProductSize ps) {
		productSizeRepo.save(ps);
		
		return "redirect:/admin/product_size/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		productSizeRepo.deleteById(id);
		
		return "redirect:/admin/product_size/search";
	}
	
	@GetMapping("/search")
	public String search(Model model, 
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "sizeID", required = false) Integer sizeID,
			@RequestParam(name = "productID", required = false) Integer productID,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {
		
		
		List<Product> products = productRepo.findAll();
		model.addAttribute("products", products);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		if (size == null) {
			size = 15;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(sizeID != null && productID == null) {
			Page<ProductSize> pageProductSize = productSizeRepo.search_sID(sizeID, pageable);

			model.addAttribute("list", pageProductSize.toList());
			model.addAttribute("totalPage", pageProductSize.getTotalPages());
		} else if(sizeID == null && productID != null) {
			Page<ProductSize> pageProductSize = productSizeRepo.search_pID(productID, pageable);

			model.addAttribute("list", pageProductSize.toList());
			model.addAttribute("totalPage", pageProductSize.getTotalPages());
		} else if (id != null) {
			ProductSize productSize  = productSizeRepo.findById(id).orElse(null);
			if (productSize != null) {
				model.addAttribute("list", Arrays.asList(productSize));
			} else
				// log
				logger.info("Id not found");
			
			model.addAttribute("totalPage", 0);
		} else {
			Page<ProductSize> pageProductSize = productSizeRepo.findAll(pageable);

			model.addAttribute("list", pageProductSize.toList());
			model.addAttribute("totalPage", pageProductSize.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("sizeID", sizeID == null ? "" : sizeID);
		model.addAttribute("productID", productID == null ? "" : productID);
		model.addAttribute("id", id == null ? "" : id);
		
		return "admin/product_size/search";
	}
}
