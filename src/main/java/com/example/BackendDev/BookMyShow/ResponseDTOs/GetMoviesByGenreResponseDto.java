package com.example.BackendDev.BookMyShow.ResponseDTOs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMoviesByGenreResponseDto {
    private int id;
    private String name;
    private int duration;
    private double ratings;
    private String language;
    private String genre;
}
