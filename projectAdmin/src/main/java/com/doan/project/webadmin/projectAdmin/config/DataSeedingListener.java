package com.doan.project.webadmin.projectAdmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.doan.project.webadmin.projectAdmin.entities.Role;
import com.doan.project.webadmin.projectAdmin.entities.User;
import com.doan.project.webadmin.projectAdmin.repository.RoleRepository;
import com.doan.project.webadmin.projectAdmin.repository.UserRepository;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (roleRepository.findById(1) == null) {
			roleRepository.save(new Role(1, "ROLE_MEMBER"));
		}
		
		if (roleRepository.findById(2) == null) {
			roleRepository.save(new Role(2, "ROLE_ADMIN"));
		}
		
		// Create admin account
		if (userRepository.findByEmail("admin1@gmail.com") == null) {
			User admin1 = new User();
			admin1.setEmail("admin1@gmail.com");
			admin1.setPassword(passwordEncoder.encode("123456a-"));
			admin1.setRole(roleRepository.getById(2));
			userRepository.save(admin1);
		}
		
		// Create admin account
		if (userRepository.findByEmail("admin2@gmail.com") == null) {
			User admin2 = new User();
			admin2.setEmail("admin2@gmail.com");
			admin2.setPassword(passwordEncoder.encode("123456a-"));
			admin2.setRole(roleRepository.getById(2));
			userRepository.save(admin2);
		}
	}
}
