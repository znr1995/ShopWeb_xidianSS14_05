package com.project_management.shoppingweb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.Admin;
import com.project_management.shoppingweb.repository.AdminRepository;

@Service
public class AdminService {
	@Resource
	AdminRepository adminRepository ;
	public Admin findByUsername(String username,String password) {
		return adminRepository.findByUsernameAndPassword(username,password);
	}
	
	public Admin findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}
}
