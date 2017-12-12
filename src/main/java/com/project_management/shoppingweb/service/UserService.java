package com.project_management.shoppingweb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.repository.UserRepository;

@Service
public class UserService {
	@Resource
	UserRepository userRepository;
	
	public User findByUserid(Long userid) {
		return userRepository.findByUserid(userid);
	}
	
	public List<User> findAllByStatus(int status) {
		return userRepository.findAllByStatus(status);
	}
}
