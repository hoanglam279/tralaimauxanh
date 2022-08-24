package com.doan.project.webadmin.projectAdmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doan.project.webadmin.projectAdmin.entities.Event;
import com.doan.project.webadmin.projectAdmin.entities.User;
import com.doan.project.webadmin.projectAdmin.repository.UserRepository;
import com.doan.project.webadmin.projectAdmin.util.ServiceUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getListUsers(Authentication auth) throws Exception {
		// get ID user login
		Integer userIdLogin = ServiceUtil.getUserIdLogin(auth, userRepository);
		
		List<User> listUsers = new ArrayList<User>();
		try {
			//listUsers = userRepository.findAll();
			listUsers = userRepository.getUsersExtract(userIdLogin, 2);
		} catch (Exception e) {
			throw e;
		}
		return listUsers;
	}
	
	public User getDetailUser(Integer id) throws Exception {
		try {
			Optional<User> optionalUser = userRepository.findById(id);
			if (!optionalUser.isPresent()) {
				throw new Exception();
			}
			return optionalUser.get();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Transactional
	public void removeUser(Integer id) throws Exception {
		userRepository.deleteById(id);
	}

}
