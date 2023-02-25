package com.example.BackendDev.BookMyShow.Models;


import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "theatreSeats")
@Data//Generates getters, setters, requiredArgs Constructor. Required Args constructor is  ~(AllArgs and NoArgs Constructor)
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Column(unique = true, nullable = false)
    private int rowNo;

    @OneToMany(mappedBy = "theatreSeat", cascade = CascadeType.ALL)
    List<TheatreSeatList> theatreSeatListRow = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Theatre theatre;
}
