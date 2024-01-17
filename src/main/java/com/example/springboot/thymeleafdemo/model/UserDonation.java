package com.example.springboot.thymeleafdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "user_donation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "donation_id")
    private int donationId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created;

    @Column(name = "money")
    private int money;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private int status;

    @Column(name = "text")
    private String text;
}
