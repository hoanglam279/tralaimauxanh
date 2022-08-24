package com.doan.project.webadmin.projectAdmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.project.webadmin.projectAdmin.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	@Modifying
	@Query(value = "update event ev set ev.status = ?1 where ev.id = ?2", nativeQuery = true)
	int setStatusForEvent(String status, Integer id);
	
	@Modifying
	@Query(value = "update event ev set ev.status_process = ?1 where ev.id = ?2", nativeQuery = true)
	int setStatusProcessForEvent(String status, Integer id);
	
	@Query(value = "select * from event ev where ev.id = ?1 and ev.id_user = ?2", nativeQuery = true)
	List<Event> getListByMySeld(Integer idEvent, Integer idUser);
}
