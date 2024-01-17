package com.example.springboot.thymeleafdemo.dao;

import com.example.springboot.thymeleafdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    public User findUserByEmailAndPassword(String email, String password);

    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.id = :id")
    public void deleteById(@Param("id") int id);
}
