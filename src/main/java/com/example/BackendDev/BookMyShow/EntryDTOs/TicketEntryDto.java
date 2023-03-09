package com.example.BackendDev.BookMyShow.EntryDTOs;

import com.example.BackendDev.BookMyShow.Enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data                      // Provides getter and setters, all constructors except NoArgs and AllArgs.
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntryDto {
    private List<String> requestedSeats;
    private int showId;
    private int userId;
}
