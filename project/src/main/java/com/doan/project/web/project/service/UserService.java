package com.doan.project.web.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doan.project.web.project.entities.User;
import com.doan.project.web.project.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
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
}
