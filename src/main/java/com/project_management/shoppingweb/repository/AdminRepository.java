package com.project_management.shoppingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByUsernameAndPassword(String username, String password);
	Admin findByEmailAndPassword(String email,String password);
	Admin findByUsername(String username);

}
