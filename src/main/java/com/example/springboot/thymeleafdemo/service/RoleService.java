package com.example.springboot.thymeleafdemo.service;


import com.example.springboot.thymeleafdemo.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();
    public Role findById(int id);
}
