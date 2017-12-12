package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User save(User user);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByUserId(long userId);
}
