package com.doan.project.web.project.config;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.doan.project.web.project.entities.Event;
import com.doan.project.web.project.repository.EventRepository;

@Component
public class ScheduledTasks {
	
	@Autowired
	private EventRepository eventRepository;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 2000)
	public void reportCurrentTime() {
		LocalDateTime now = LocalDateTime.now();  
		
		// get list events
		List<Event> listEvents = new ArrayList<Event>();
		listEvents = eventRepository.findAll();
		
		System.out.println("size : " + now);
		
		for (int i = 0; i < listEvents.size(); i++) {
			if(listEvents.get(i).getStatus().equals("1")) {
				if (now.isAfter(listEvents.get(i).getTimeStart()) && now.isBefore(listEvents.get(i).getTimeEnd())) {
					listEvents.get(i).setStatusProcess("2");
				} else if (now.isAfter(listEvents.get(i).getTimeEnd())){
					listEvents.get(i).setStatusProcess("3");
				}
			}
		}
		
		eventRepository.saveAll(listEvents);
		
	}

}
