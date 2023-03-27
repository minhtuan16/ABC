//package com.example.demo.controller;
//
//import java.text.ParseException;
//import java.util.Arrays;
//
//import javax.annotation.security.RolesAllowed;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.example.demo.entity.User;
//import com.example.demo.repository.UserRepo;
//import com.example.demo.service.MailService;
//
//@Controller
//@RequestMapping("/admin/user")
//public class UserControllerr {
//	
//	private static Logger logger = LoggerFactory.getLogger(UserControllerr.class);
//	
//	@Autowired
//	UserRepo userRepo;
//	
//	@Autowired
//	MailService mailService;
//	
//	@GetMapping("/create")
////	@RolesAllowed(value = { "ROLE_Admin" })
//	public String create(Model model, HttpServletRequest request) {
//		// check vai tro admin
////		org.springframework.security.core.userdetails.User currentUser = 
////				(org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////		System.out.println(currentUser.getUsername());
//		
////		if (request.isUserInRole("Admin")) {
////			System.out.println("Admin");
////		}
//		
//		model.addAttribute("userrr", new User());
//		return "admin/user/create";
//	}
//	
//	@PostMapping("/create")
//	public String create(@ModelAttribute("userrr") @Valid User user, BindingResult bindingResult) {
//		
//		if(bindingResult.hasErrors()) {
//			return "admin/user/create";
//		}
//		
//		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//		
//		userRepo.save(user);
//		
//		// mailService.sendEmail(user.getMailUser(), "Shop Shoes", "Chuc mung ban dang ki thanh cong tai khoan !");
//		return "redirect:/admin/user/search";
//	}
//	
//	@GetMapping("/update")
//	public String update(@RequestParam("id") int id, Model model) {
//		User user = userRepo.getById(id);
//		
//		model.addAttribute("user", user);
//		return "admin/user/update";
//	}
//	
//	@PostMapping("/update")
//	public String update(@ModelAttribute User user) throws ParseException{
//		
//		userRepo.save(user);
//		
//		return "redirect:/admin/user/search";
//	}
//	
//	@GetMapping("/delete")
//	public String delete(@RequestParam("id") int id) {
//		userRepo.deleteById(id);
//		
//		return "redirect:/admin/user/search";
//	}
//	
//	@GetMapping("/search")
////	@RolesAllowed(value = { "ROLE_Member" })
//	public String search(Model model,
//			@RequestParam(name = "id", required = false) Integer id,
//			@RequestParam(name = "username", required = false) String username,
//			@RequestParam(name = "page", required = false) Integer page,
//			@RequestParam(name = "size", required = false) Integer size,
//			HttpServletRequest request) {
//		
////		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////		System.err.println(currentUser.getUsername() + " " + currentUser.getPassword());
////		
////		if(request.isUserInRole("Member")) {
////			System.out.println("Member");
////		}
//		
//		if(size == null) {
//			size = 10;
//		}
//		
//		if(page == null) {
//			page = 0;
//		}
//		
//		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
//		
//		if(username != null && !username.isEmpty()) {
//			Page<User> pageUser = userRepo.searchUsername("%" + username + "%", pageable);
//			model.addAttribute("list", pageUser.toList());
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//		} else if (id != null) {
//			User user = userRepo.findById(id).orElse(null);
//			if(user != null) {
//				model.addAttribute("list", Arrays.asList(user));
//			} else {
//				logger.info("id not found !");
//			}
//			model.addAttribute("totalPage", 0);
//		} else {
//			Page<User> pageUser = userRepo.findAll(pageable);
//			
//			model.addAttribute("list", pageUser.toList());
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//		}
//		
//		model.addAttribute("page", page);
//		model.addAttribute("size", size);
//		model.addAttribute("id", id == null ? "" : id);
//		model.addAttribute("username", username == null ? "" : username);
//		
//		
//		return "admin/user/search";
//	}
//}

package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;

@Controller

@RequestMapping("/admin/user")
public class UserControllerr {

	private static Logger logger = LoggerFactory.getLogger(UserControllerr.class);

	@Autowired
	UserRepo userRepo;

	@GetMapping("/create")
//	@RolesAllowed(value = { "ROLE_Admin" })
	public String create(Model model, HttpServletRequest request) {
//		// check vai tro admin
//		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
//				.getContext().getAuthentication().getPrincipal();
//		System.out.println(currentUser.getUsername());
//
//		if (request.isUserInRole("Admin")) {
//			System.out.println("Admin");
//		}

		model.addAttribute("userrr", new User());
		return "admin/user/create";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("userrr") @Valid User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "admin/user/create";
		}

		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

		userRepo.save(user);

		return "redirect:/admin/user/search";
	}

	@GetMapping("/update")
//	@RolesAllowed(value = { "ROLE_Admin" })
	public String update(@RequestParam("id") int id, Model model, HttpServletRequest request) {

//		 check vai tro admin
//		org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder
//				.getContext().getAuthentication().getPrincipal();
//		System.out.println(currentUser.getUsername());
//
//		if (request.isUserInRole("Admin")) {
//			System.out.println("Admin");
//		}

		User user = userRepo.getById(id);

		model.addAttribute("user", user);
		return "admin/user/update";
	}

	@PostMapping("/update")
//	public String update(@RequestParam("id") int id, @RequestParam("name") String name) {
//	2 cai tuong tu nhau

	public String update(@ModelAttribute User user) throws ParseException { // no map thang vao doi tuong Student va set
																			// thang gia tri vao id
		// va name

		userRepo.save(user);

		return "redirect:/admin/user/search";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		userRepo.deleteById(id);

		return "redirect:/admin/user/search"; // doc ra man hinh danh sach

	}

	@GetMapping("/search")
	public String search(Model model,
//			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size) {

		// neu khong co required = false thi loi duong truyen
		// khi co required = false thi truyen String hay khong cung khong anh huong

		if (size == null) {
			size = 10;
		}

		if (page == null) {
			page = 0;
		}

		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

		if (username != null && !username.isEmpty()) {
			Page<User> pageUser = userRepo.searchUsername("%" + username + "%", pageable);

			model.addAttribute("list", pageUser.toList());
			model.addAttribute("totalPage", pageUser.getTotalPages());
		} else if (id != null) {
			User user = userRepo.findById(id).orElse(null);
			if (user != null) {
				model.addAttribute("list", Arrays.asList(user));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<User> pageUser = userRepo.findAll(pageable);
			model.addAttribute("list", pageUser.toList());
			model.addAttribute("totalPage", pageUser.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("username", username == null ? "" : username);
		return "admin/user/search";

	}

}
