package com.example.springboot.thymeleafdemo.dao;

import com.example.springboot.thymeleafdemo.model.Donation;
import com.example.springboot.thymeleafdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    public List<Donation> findAllByOrderByNameAsc();

    @Modifying
    @Query(value = "DELETE FROM Donation d WHERE d.id = :id")
    public void deleteById(@Param("id") int id);
}
