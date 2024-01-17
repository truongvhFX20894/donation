package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(int id);
    public void save(User user);
    public void deleteById(int id);
    public User findUserByEmailAndPassword(String email, String password);
}
