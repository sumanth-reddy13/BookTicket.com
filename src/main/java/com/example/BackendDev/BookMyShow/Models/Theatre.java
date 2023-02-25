package com.example.BackendDev.BookMyShow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theatres")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Theatre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "theatreId")
    private int id;

    private String name;
    private String location;

    @ManyToOne
    @JoinColumn
    Movie movie;                 // unidirectional mapping with Movie.

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)    // Bidirectional mapping with theatreSeat.
    List<TheatreSeat> listOfTheatreSeats = new ArrayList<>();

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    List<Show> listOfShowsInATheatre = new ArrayList<>();           // Bidirectional mapping with Show.
}
