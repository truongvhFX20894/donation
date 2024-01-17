package com.example.springboot.thymeleafdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "name")
    private String name;

    @Column(name = "money")
    private int money;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "status")
    private int status;

    @Column(name = "organization_name")
    private String organizationName;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "user_donation",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "donation_id")
    private List<UserDonation> userDonationList;
}
