package com.example.BackendDev.BookMyShow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ticketId")
    private int id;
    private int totalAmount;
    private LocalDate showDate;
    private LocalTime showTime;

//    private String ticketId = UUID.randomUUID().toString();
    private String theatreName;
    private String movieName;

    @ManyToOne
    @JoinColumn
    User user;                        // unidirectional mapping with User.

    @ManyToOne
    @JoinColumn
    Show show;                        // unidirectional mapping with Show.
}
