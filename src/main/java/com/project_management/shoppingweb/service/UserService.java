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
	
	public User findByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}
	
	public List<User> findAllByState(Integer state) {
		return userRepository.findAllByState(state);
	}
}
