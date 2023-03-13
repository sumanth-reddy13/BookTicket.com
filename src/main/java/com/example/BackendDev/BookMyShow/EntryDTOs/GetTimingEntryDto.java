package com.example.BackendDev.BookMyShow.EntryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTimingEntryDto {
    private int movieId;
    private int theatreId;
}
