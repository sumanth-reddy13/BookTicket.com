package com.example.BackendDev.BookMyShow.Models;

import com.example.BackendDev.BookMyShow.Enums.Genre;
import com.example.BackendDev.BookMyShow.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "movieName", unique = true)
    private String name;

    @Column(name = "runTime")
    private int duration;

    private double ratings;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)    // Bidirectional Mapping with Theatre
    List<Theatre> theatreList = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)    // Bidirectional Mapping with Show
    List<Show> showList = new ArrayList<>();
}
