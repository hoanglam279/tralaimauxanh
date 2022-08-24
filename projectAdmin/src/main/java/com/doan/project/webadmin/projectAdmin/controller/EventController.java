package com.doan.project.webadmin.projectAdmin.controller;

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

import com.doan.project.webadmin.projectAdmin.entities.Event;
import com.doan.project.webadmin.projectAdmin.entities.EventJoins;
import com.doan.project.webadmin.projectAdmin.repository.UserRepository;
import com.doan.project.webadmin.projectAdmin.service.EventService;

@Controller
//@CrossOrigin(origins = "*")
//@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/detailEvent/{id}")
	public String getDetailEvent(Model model, @PathVariable("id") Integer id, Authentication auth) throws Exception {
		
		Event event = new Event();
		try {
			event = eventService.getDetailEvent(id);
		} catch (Exception e) {
		}
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", false);
		} else {
			model.addAttribute("isLogin", true);
		}
		model.addAttribute("event", event);
		model.addAttribute("eventJoin", new EventJoins());
		
		return "detailEvent";

		/*
		 * Optional<Event> eventOptional = eventService.getDetailEvent(id); return
		 * eventOptional.map(event -> new ResponseEntity<>(event, HttpStatus.OK))
		 * .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		 */
	}

	@GetMapping("/getListEvents")
	public String getDetailEvent(Model model, Authentication auth) {

		List<Event> listEvents = new ArrayList<Event>();
		try {
			listEvents = eventService.getEvents();
			for (int i = 0; i< listEvents.size(); i++) {
				// get number persion da tham gia su kien
				int numberUserJoined = eventService.getNumberUserJoined(listEvents.get(i).getId());
				listEvents.get(i).setNumberUserJoined(numberUserJoined);
				if (numberUserJoined >= listEvents.get(i).getUmberUserJoin()) {
					listEvents.get(i).setStatusMax("1");
				} else {
					listEvents.get(i).setStatusMax("0");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		if (auth == null || auth.getName() == null || auth.getName() == "") {
			model.addAttribute("isLogin", false);
		} else {
			model.addAttribute("isLogin", true);
		}
		model.addAttribute("events", listEvents);
		return "index";

		// return new ResponseEntity<>(eventService.getEvents(), HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public String createEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("add", true);
		return "editCreateEvent";
	}
	
	@GetMapping("/edit/{idEvent}")
	public String editEvent(Model model, @PathVariable("idEvent") Integer id) {
		Event event = new Event();
		try {
			event = eventService.getDetailEvent(id);
		} catch (Exception e) {

		}
		model.addAttribute("event", event);
		model.addAttribute("add", false);
		return "editCreateEvent";
	}
	
	@GetMapping("/approvalEvent/{idEvent}")
	public String approvalEvent(Model model, @PathVariable("idEvent") Integer id) throws Exception {
		try {
			eventService.approvalEvent(id);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/event/getListEvents";
	}

	@PostMapping("/addUpdateEvent")
	public String addUpdateEvent(@Validated Event event, BindingResult result, Model model) throws Exception {
		// public ResponseEntity<Event> addUpdateEvent(@RequestBody Event event) throws
		// Exception {

		/*
		 * if (result.hasErrors()) { throw new Exception(); }
		 */
		try {
			eventService.addUpdateEvent(event);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/event/getListEvents";

		/*
		 * if (event.getId() == null) { return new
		 * ResponseEntity<>(eventService.addUpdateEvent(event), HttpStatus.OK); } else {
		 * Optional<Event> eventOptional = eventService.getDetailEvent(event.getId());
		 * return eventOptional.map(eventItem -> { event.setId(eventItem.getId());
		 * return new ResponseEntity<>(eventService.addUpdateEvent(event),
		 * HttpStatus.OK); }).orElseGet(() -> new
		 * ResponseEntity<>(HttpStatus.NOT_FOUND)); }
		 */
	}

	@GetMapping("deleteEvent/{id}")
	public String deleteEvent(@PathVariable Integer id) throws Exception {
		/*
		 * Optional<Event> eventOptional = eventService.getDetailEvent(id); return
		 * eventOptional.map(category -> { eventService.removeEvent(id); return new
		 * ResponseEntity<>(category, HttpStatus.OK); }).orElseGet(() -> new
		 * ResponseEntity<>(HttpStatus.NOT_FOUND));
		 */
		try {
			eventService.removeEvent(id);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/event/getListEvents";
	}
}
