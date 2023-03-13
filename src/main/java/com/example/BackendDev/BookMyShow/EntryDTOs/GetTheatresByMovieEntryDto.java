package com.example.BackendDev.BookMyShow.EntryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTheatresByMovieEntryDto {
    private String movieName;
}
