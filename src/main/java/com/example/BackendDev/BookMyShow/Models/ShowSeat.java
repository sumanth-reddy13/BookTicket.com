package com.example.BackendDev.BookMyShow.Models;

import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "showSeats")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowSeat {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    private int price;
    private boolean isBooked;
    @Column(unique = true)
    private int seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @CreationTimestamp
    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    Show show;              // unidirectional mapping with show.
}
