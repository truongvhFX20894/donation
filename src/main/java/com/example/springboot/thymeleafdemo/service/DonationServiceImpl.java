package com.example.springboot.thymeleafdemo.service;

import com.example.springboot.thymeleafdemo.dao.DonationRepository;
import com.example.springboot.thymeleafdemo.model.Donation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    private DonationRepository donationRepository;
    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Donation findById(int id) {
        Optional<Donation> result = donationRepository.findById(id);
        Donation donation = null;
        if (result.isPresent()) {
            donation = result.get();
        }
        return donation;
    }

    @Transactional
    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Transactional
    @Override
    public void update(Donation donation) {

    }


    @Transactional
    @Override
    public void deleteById(int id) {
        donationRepository.deleteById(id);
    }
}
