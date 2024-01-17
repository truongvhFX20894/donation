package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.model.Donation;

import java.util.List;

public interface DonationService {
    public List<Donation> findAll();
    public Donation findById(int id);
    public void save(Donation donation);
    public void update(Donation donation);
    public void deleteById(int id);
}
