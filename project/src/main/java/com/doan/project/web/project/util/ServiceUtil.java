package com.doan.project.web.project.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;

import com.doan.project.web.project.entities.Event;
import com.doan.project.web.project.entities.User;
import com.doan.project.web.project.repository.EventRepository;
import com.doan.project.web.project.repository.UserRepository;

public class ServiceUtil {

	public static Optional<Event> checkEventExist(EventRepository eventRepository,
			Integer id ) throws Exception {
		try {
			Optional<Event> eventOptional = eventRepository.findById(id);
			if (!eventOptional.isPresent()) {
				throw new Exception();
			}
			return eventOptional;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public static User getUserLogin(Authentication auth, UserRepository userRepository) {
		User user = userRepository.findByEmail(auth.getName());
		return user;
	}
	
	public static Integer getUserIdLogin(Authentication auth, UserRepository userRepository) throws Exception {
		User user = userRepository.findByEmail(auth.getName());
		if(user != null) {
			return user.getId();
		} else {
			throw new Exception();
		}
	}
}
