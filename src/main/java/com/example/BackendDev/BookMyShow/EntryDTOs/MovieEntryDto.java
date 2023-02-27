package com.example.BackendDev.BookMyShow.EntryDTOs;
import com.example.BackendDev.BookMyShow.Enums.Genre;
import com.example.BackendDev.BookMyShow.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntryDto {

    private String name;

    private int duration;

    private double ratings;

    private Language language;

    private Genre genre;
}
