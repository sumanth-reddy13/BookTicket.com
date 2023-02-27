package com.example.BackendDev.BookMyShow.EntryDTOs;

import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                      // Provides getter and setters, all constructors except NoArgs and AllArgs.
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntryDto {
    private int noOfTickets;
    private int movieId;
    private int theatreId;
    private int showId;
    private int userId;
    private SeatType seatType;
}
