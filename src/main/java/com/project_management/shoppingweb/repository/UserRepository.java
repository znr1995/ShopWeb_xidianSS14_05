package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //Register_user
    //changeinf
    User save(User user);
    //loginByEmail
    //loginByUsername
    User findByEmailAndPassword(String Email,String Password);
    User findByUsernameAndPassword(String Username,String Password);
    
	User findByUserid(Long userid);
	List<User> findAllByStatus(int status);

}
