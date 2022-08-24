package com.doan.project.webadmin.projectAdmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doan.project.webadmin.projectAdmin.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	//getUsersExtract
	@Query(value = "select * from user where id != ?1 and id_role != ?2", nativeQuery = true)
	List<User> getUsersExtract(Integer userIdLogin, Integer role);
}
