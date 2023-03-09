package com.example.BackendDev.BookMyShow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userId")
    private int id;

    @Column(name = "userName")
    private String name;

    @Column(name = "mobileNumber", unique = true, nullable = false)
    private String mobile;

    @Column(name = "emailId", unique = true, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Ticket> bookedTickets = new ArrayList<>();                     // Bidirectional mapping with Ticket

}
