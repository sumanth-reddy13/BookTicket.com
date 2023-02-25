package com.example.BackendDev.BookMyShow.EntryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreEntryDto {
    private String name;
    private String location;
    private int recliner;
    private int classic;
}
