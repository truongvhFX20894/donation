package com.example.springboot.thymeleafdemo.dao;

import com.example.springboot.thymeleafdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}