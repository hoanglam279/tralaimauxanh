package com.doan.project.web.project.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doan.project.web.project.entities.Role;
import com.doan.project.web.project.entities.User;
import com.doan.project.web.project.repository.UserRepository;
import com.doan.project.web.project.service.UserDetailsServiceImpl;
import com.doan.project.web.project.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/403")
	public String accessDenied(Model model) {
		return "403";
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		return "login";
	}
	
	@GetMapping("/register")
	public String loginRegister(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";

	}
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/main";
	}
	
	@PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Validated User user,
        BindingResult result) {
		
		System.out.println("registration");
		  User existing = userRepository.findByEmail(user.getEmail());
		  if (existing != null) { 
			  result.rejectValue("email", null, "There is already an account registered with that email"); }
		  
		  if (result.hasErrors()) {
			  System.out.println("error");
			  return "register";
		  }
		  Role role = new Role(1, "ROLE_MEMBER");
		  user.setRole(role);
		  
		  user.setPassword(passwordEncoder.encode(user.getPassword()));
		  userRepository.save(user);
		  
        return "redirect:/user/login";
    }
	
	@GetMapping("/detailUser/{id}")
	public String getDetailUser(Model model, @PathVariable("id") Integer id, Authentication auth) throws Exception {
		
		User user = new User();
		try {
			user = userService.getDetailUser(id);
		} catch (Exception e) {
		}
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", false);
		} else {
			model.addAttribute("isLogin", true);
		}
		model.addAttribute("user", user);
		
		return "detailUser";
	}

}
