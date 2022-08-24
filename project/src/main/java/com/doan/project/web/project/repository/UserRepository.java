package com.doan.project.web.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.project.web.project.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	@Query(value = "select us.* from event_joins evjs join user us on us.id = evjs.id_user where evjs.id_event = ?1", nativeQuery = true)
	List<User> getListUserJoined(Integer idEvent);
}
