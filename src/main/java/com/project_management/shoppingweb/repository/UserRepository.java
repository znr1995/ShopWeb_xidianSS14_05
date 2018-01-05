package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.User;



import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    User findByUserId(long userId);

    //Register_user
    //changeinf
    User save(User user);
    //loginByEmail
    //loginByUsername
    User findByEmailAndPassword(String Email,String Password);
    User findByUsernameAndPassword(String Username,String Password);

	User findByUserId(Long userId);
	List<User> findAllByState(Integer state);
	List<User> findAllByUsernameLike(@Param("username") String username);
}