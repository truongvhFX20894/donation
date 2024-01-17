package com.example.springboot.thymeleafdemo.dao;

import com.example.springboot.thymeleafdemo.model.User;
import com.example.springboot.thymeleafdemo.model.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
    @Modifying
    @Query(value = "DELETE FROM UserDonation u WHERE u.donationId = :id")
    public void deleteUserDonationByDonationId(@Param("id") int id);
}
