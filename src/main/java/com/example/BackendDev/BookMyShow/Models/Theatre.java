package com.example.BackendDev.BookMyShow.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity                     // This Annotation creates the table in the database.
@Table(name = "theatres")
@NoArgsConstructor
@AllArgsConstructor
@Data                       // Provides getters & setters, and All constructors except NoArgs & AllArgs.
@Builder                    // Annotation is used to build an Entity.
public class Theatre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "theatreId")
    private int id;

    private String name;
    private String location;

    @ManyToMany
    @JoinTable(name = "MTM",
            joinColumns = @JoinColumn(name = "theatreId"),
            inverseJoinColumns = @JoinColumn(name = "movieId")
    )
    List<Movie> movieList;               // ManyToMany Mapping with Movie.

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    List<TheatreSeat> theatreSeatList = new ArrayList<>();      // Bidirectional with TheatreSeat.

    @OneToMany(mappedBy = "theatre", cascade = CascadeType.ALL)
    List<Show> listOfShowsInATheatre;                 // Bidirectional mapping with Show.
}
