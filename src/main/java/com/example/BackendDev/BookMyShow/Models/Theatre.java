package com.example.BackendDev.BookMyShow.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "theatres")
@NoArgsConstructor
@AllArgsConstructor
@Data
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

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    List<Show> listOfShowsInATheatre;                 // Bidirectional mapping with Show.
}
