package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import com.example.demo.repository.SizeRepo;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	SizeRepo sizeRepo;
	
	
//	@GetMapping("/products-details")
//	@RolesAllowed(value = { "ROLE_Member" })
//	public String productsDetails(HttpServletRequest request) {
//		//check xem user da co tai khoan member chua
//		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.err.println(currentUser.getUsername());
//		
//		if(request.isUserInRole("Member")) {
//			System.out.println("Member");
//		}
//		return "shopStore/products-details";
//	}
	
	@GetMapping("/create")
	public String create(Model model) {
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		model.addAttribute("pro", new Product());
		return "/admin/product/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute("pro") @Valid Product product,
			@RequestParam(name = "file", required = false) MultipartFile file,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "/admin/product/create";
		}
		
		// luu lai file vao 1 folder, sau do lay url save to db
		if(file != null && file.getSize() > 0) {
			try {
				final String folder = "D:/JavaCore/ShopShoes/picture/upload";
				String originFilename = file.getOriginalFilename();
			
				File newFile = new File(folder + "/" +originFilename);
			
				// copy noi dung file upload vao file new
				file.transferTo(newFile);
				
				//luu lai vao db
				product.setImageURL(originFilename);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		productRepo.save(product);
		
		return "redirect:/admin/product/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) throws  IOException {
		
		Product product = productRepo.getById(id);
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		model.addAttribute("product", product);
		return "/admin/product/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Product product) {
		
		
		productRepo.save(product);
		
		return "redirect:/admin/product/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		productRepo.deleteById(id);
		
		return "redirect:/admin/product/search";
	}
	
	@GetMapping("/search")
	public String search(Model model ,
			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "name", required = false) String name ,
			@RequestParam(name = "categoryID", required = false) Integer categoryID ,
			@RequestParam(name = "page", required = false) Integer page ,
			@RequestParam(name = "size", required = false) Integer size ) {
		
		List<Category> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);
		
		List<Size> sizes = sizeRepo.findAll();
		model.addAttribute("sizes", sizes);
		
		if(size == null) {
			size = 10;
		}
		
		if(page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(categoryID != null && name != null && !name.isEmpty()) {
			Page<Product> pageProduct = productRepo.searchCategoryIdAndNameP(categoryID, "%" + name + "%", pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (categoryID != null) {
			Page<Product> pageProduct = productRepo.searchCategoryId(categoryID, pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (categoryID == null && name != null && !name.isEmpty()) {
			Page<Product> pageProduct = productRepo.searchNameP("%" + name + "%", pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (id != null) {
			Product product = productRepo.findById(id).orElse(null);
			if(product != null) {
				model.addAttribute("list", Arrays.asList(product));
			} else {
				logger.info("id not found");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<Product> pageProduct = productRepo.findAll(pageable);
			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		}
		 
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("categoryID", categoryID == null ? "" : categoryID);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("id", id == null ? "" : id);
		
		return "/admin/product/search";
	} 
	
	
	@GetMapping("/download")
	public void download(@RequestParam("imageURL") String imageURL
			, HttpServletResponse response) {
		final String folder = "D:/JavaCore/ShopStore/picture/upload";
		
		File file = new File(folder + "/" + imageURL);
		if(file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
