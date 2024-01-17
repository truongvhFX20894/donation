package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.dao.RoleRepository;
import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        Optional<Role> result = roleRepository.findById(id);
        Role role = null;
        if (result.isPresent()) {
            role = result.get();
        }
        return role;
    }
}
