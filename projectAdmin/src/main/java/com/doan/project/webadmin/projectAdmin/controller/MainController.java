package com.doan.project.webadmin.projectAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@CrossOrigin(origins = "*")
//@RestController
@RequestMapping("/")
public class MainController {
	
	@GetMapping("/")
	public String getMainPageRedirect() throws Exception {
		return "redirect:/event/getListEvents";
		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
	
}
