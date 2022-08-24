package com.doan.project.web.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.project.web.project.entities.Event;
import com.doan.project.web.project.entities.EventJoins;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	@Modifying
	@Query(value = "update event ev set ev.status = ?1 where ev.id = ?2", nativeQuery = true)
	int setStatusForEvent(String status, Integer id);

	@Query(value = "select * from event ev where ev.id_user = ?1", nativeQuery = true)
	List<Event> getListMyEvents(Integer idUser);

	@Query(value = "select * from event ev where ev.id_user != ?1 and ev.status = ?2 and ev.status_process = ?2 and NOT EXISTS ( select 1 from event_joins evjs where evjs.id_user = ?1 and evjs.id_event = ev.id)", nativeQuery = true)
	List<Event> getListEventsExtract(Integer idUser, String status);
	
	@Query(value = "select * from event ev where EXISTS ( select 1 from event_joins evjs where evjs.id_user = ?1 and evjs.id_event = ev.id)", nativeQuery = true)
	List<Event> getListEventsJoined(Integer idUser);
	
	@Query(value = "select * from event ev where ev.id = ?1 and ev.id_user = ?2", nativeQuery = true)
	List<Event> getListByMySeld(Integer idEvent, Integer idUser);
	
	@Query(value = "select ev.* from event_joins evjs join event ev on ev.id = evjs.id_event where evjs.id_user = ?1 and evjs.status_notify = ?2", nativeQuery = true)
	List<Event> getListNotifyApprovalJoin(Integer idEvent, String statusNotify);
}
