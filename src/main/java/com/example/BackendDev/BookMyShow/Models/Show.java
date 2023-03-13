package com.example.BackendDev.BookMyShow.Models;

import com.example.BackendDev.BookMyShow.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Show {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "showId")
    private int id;

//    @Column(name = "showTime", columnDefinition = "time")
    private LocalTime showTime;
    private LocalDate showDate;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

//    @Column(name = "movieId")
    @ManyToOne
    @JoinColumn
    Movie movie;              // unidirectional mapping with Movie.

//    @Column(name = "theatreId")
    @ManyToOne
    @JoinColumn
    Theatre theatre;           // unidirectional mapping with Theatre.

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    List<ShowSeat> showSeatList = new ArrayList<>();                // Bidirectional mapping with ShowSeat.

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    List<Ticket> bookedTickets = new ArrayList<>();              // Bidirectional mapping with Ticket.

}
