package com.doan.project.web.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.project.web.project.entities.Event;
import com.doan.project.web.project.entities.EventJoins;
import com.doan.project.web.project.entities.User;
import com.doan.project.web.project.repository.EventRepository;
import com.doan.project.web.project.repository.UserRepository;
import com.doan.project.web.project.service.EventService;

@Controller
//@CrossOrigin(origins = "*")
//@RestController
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/")
	public String getMainPageRedirect() throws Exception {
		return "redirect:/main";
		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
	
	@GetMapping("/main")
	public String getMainPage(Model model, Authentication auth) throws Exception {
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", false);
		} else {
			model.addAttribute("isLogin", true);
			// get list notify
			List<Event> listNotify = eventService.getListNotify(auth);
			boolean isHaveNotify = listNotify.size() > 0 ? true : false;
			model.addAttribute("isHaveNotify", isHaveNotify);
			model.addAttribute("listNotify", listNotify);
		}
		
		return "main";
		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
	
	@GetMapping("/information")
	public String getInformation(Model model, Authentication auth) throws Exception {
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", false);
		} else {
			model.addAttribute("isLogin", true);
		}
		return "information";
		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
	
	@GetMapping("/network")
	public String getNetwork(Model model, Authentication auth) throws Exception {
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", "0");
		} else {
			model.addAttribute("isLogin", "1");
		}
		return "network";
		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
}
