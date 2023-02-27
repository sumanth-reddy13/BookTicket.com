package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.MovieEntryDtoToMovieEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.MovieEntryDto;
import com.example.BackendDev.BookMyShow.Models.Movie;
import com.example.BackendDev.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{

        Movie movie = MovieEntryDtoToMovieEntity.EntryDtoToMovie(movieEntryDto);
        movieRepository.save(movie);
        return "movie added";
    }
}
