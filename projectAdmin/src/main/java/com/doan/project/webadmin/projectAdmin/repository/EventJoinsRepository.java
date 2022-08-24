package com.doan.project.webadmin.projectAdmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.project.webadmin.projectAdmin.entities.EventJoins;

@Repository
public interface EventJoinsRepository extends JpaRepository<EventJoins, Integer>{
	@Query(value = "select count(1) from event_joins evjs where evjs.id_event = ?1", nativeQuery = true)
	int countUserJoined(Integer idEvent);
}
