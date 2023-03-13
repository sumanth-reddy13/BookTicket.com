package com.example.BackendDev.BookMyShow.ResponseDTOs;

import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Enums.ShowType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTheatresByLocDto {
    private String location;
    private String theatreName;
    private String movieName;
    private int showId;
    private LocalTime showTime;
    private LocalDate showDate;
    @Enumerated(value = EnumType.STRING)
    private ShowType showType;



}
