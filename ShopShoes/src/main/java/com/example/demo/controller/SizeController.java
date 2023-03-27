package com.example.demo.controller;

import java.util.Arrays;

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

import com.example.demo.entity.Size;
import com.example.demo.repository.SizeRepo;

@Controller
@RequestMapping("/admin/size")
public class SizeController {
	private static Logger logger = LoggerFactory.getLogger(SizeController.class);
	
	@Autowired
	SizeRepo sizeRepo;
	

	@GetMapping("/create")
	public String create(Model model) {
		
		return "admin/size/create";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute Size size) {
		
		sizeRepo.save(size);
		
		return "redirect:/admin/size/search";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam("id") int id, Model model) {
		
		Size size = sizeRepo.getById(id);
		
		model.addAttribute("size", size);
		return "admin/size/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Size size) {
		sizeRepo.save(size);
		
		return "redirect:/admin/size/search";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		sizeRepo.deleteById(id);
		return "redirect:/admin/size/search";
	}
	
	@GetMapping("/search")
	public String search(Model model , 
			@RequestParam(name = "id", required = false) Integer id ,
			@RequestParam(name = "nameSize", required = false) String nameSize ,
			@RequestParam(name = "page", required = false) Integer page ,
			@RequestParam(name = "size", required = false) Integer size) {
		
		if(size == null) {
			size = 15;
		}
		
		if(page == null) {
			page = 0;
		}
		
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		
		if(nameSize != null && !nameSize.isEmpty()) {
			Page<Size> pageSize = sizeRepo.searchNameSize("%" + nameSize + "%", pageable);
			
			model.addAttribute("list", pageSize.toList());
			model.addAttribute("totalPage", pageSize.getTotalPages());
		} else if(id != null) {
			Size sizee = sizeRepo.findById(id).orElse(null);
			if (sizee != null) {
				model.addAttribute("list", Arrays.asList(sizee));
			} else {
				logger.info("id not found !");
			}
			model.addAttribute("totalPage", 0);
		} else {
			Page<Size> pageSize = sizeRepo.findAll(pageable);
			model.addAttribute("list", pageSize.toList());
			model.addAttribute("totalPage", pageSize.getTotalPages());
		}
		
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("nameSize", nameSize == null ? "" : nameSize);
		
		return "admin/size/search";
	}
}
