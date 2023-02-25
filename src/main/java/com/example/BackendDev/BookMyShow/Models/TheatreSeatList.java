package com.example.BackendDev.BookMyShow.Models;

import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "theatreSeatRow")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TheatreSeatList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Column(unique = true, nullable = false)
    private int seatNo;

    @ManyToOne
    @JoinColumn
    TheatreSeat theatreSeat;

//    @ManyToOne
//    @JoinColumn
//    Theatre theatre;
}
