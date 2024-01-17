package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.dao.UserDonationRepository;
import com.example.springboot.thymeleafdemo.dao.UserRepository;
import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.User;
import com.example.springboot.thymeleafdemo.model.UserDonation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService {
    private UserDonationRepository userDonationRepository;

    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository) {
        this.userDonationRepository = userDonationRepository;
    }


    @Override
    public List<UserDonation> findAll() {
        return userDonationRepository.findAll();
    }

    @Override
    public UserDonation findById(int id) {
        Optional<UserDonation> result = userDonationRepository.findById(id);
        UserDonation userDonation = null;
        if (result.isPresent()) {
            userDonation = result.get();
        }
        return userDonation;
    }

    @Transactional
    @Override
    public void save(UserDonation userDonation) {
        userDonationRepository.save(userDonation);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        userDonationRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByDonationId(int id) {
        userDonationRepository.deleteUserDonationByDonationId(id);
    }
}
