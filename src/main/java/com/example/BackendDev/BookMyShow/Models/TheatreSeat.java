package com.example.BackendDev.BookMyShow.Models;


import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "theatreSeats")
@AllArgsConstructor
@NoArgsConstructor
@Data           //Generates getters, setters, requiredArgs Constructor. Required Args constructor is  ~(AllArgs and NoArgs Constructor)

public class TheatreSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Column(nullable = false)
    private String seatNo;

    @ManyToOne
    @JoinColumn
    Theatre theatre;
}
