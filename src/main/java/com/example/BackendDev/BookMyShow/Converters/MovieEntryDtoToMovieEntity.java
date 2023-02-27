package com.example.BackendDev.BookMyShow.Converters;

import com.example.BackendDev.BookMyShow.EntryDTOs.MovieEntryDto;
import com.example.BackendDev.BookMyShow.Models.Movie;

public class MovieEntryDtoToMovieEntity {
    public static Movie EntryDtoToMovie(MovieEntryDto movieEntryDto) {

        Movie movie = Movie.builder()
                .genre(movieEntryDto.getGenre())
                .name(movieEntryDto.getName())
                .duration(movieEntryDto.getDuration())
                .ratings(movieEntryDto.getRatings())
                .language(movieEntryDto.getLanguage()).build();

        return movie;
    }
}
