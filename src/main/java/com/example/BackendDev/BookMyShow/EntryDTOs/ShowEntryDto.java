package com.example.BackendDev.BookMyShow.EntryDTOs;

import com.example.BackendDev.BookMyShow.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowEntryDto {
    private String showTime;
    private String showDate;
    private ShowType showType;
    private int movieId;
    private int theatreId;
    private int ticketPrice;
}
