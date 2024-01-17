package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.UserDonation;

import java.util.List;

public interface UserDonationService {
    public List<UserDonation> findAll();
    public UserDonation findById(int id);
    public void save(UserDonation userDonation);
    public void deleteById(int id);
    public void deleteByDonationId(int id);
}
