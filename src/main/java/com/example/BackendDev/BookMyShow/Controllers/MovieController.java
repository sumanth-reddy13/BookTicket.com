package com.example.BackendDev.BookMyShow.Controllers;

import com.example.BackendDev.BookMyShow.EntryDTOs.GetMoviesByGenreEntryDto;
import com.example.BackendDev.BookMyShow.EntryDTOs.GetTheatresByMovieEntryDto;
import com.example.BackendDev.BookMyShow.EntryDTOs.MovieEntryDto;
import com.example.BackendDev.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto) {
        try {
            String response = movieService.addMovie(movieEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e) {
            String response = "Failed to add the Movie";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getMovieWithMaxShows")
    public ResponseEntity getMaxShows() {
        try {
            return new ResponseEntity<>(movieService.getMovieWithMaxShows(), HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTheatresByMovie")
    public ResponseEntity getTheatresByMovie(@RequestBody GetTheatresByMovieEntryDto getTheatresByMovieEntryDto) {
        try {
            return new ResponseEntity<>(movieService.getTheatresOfAMovie(getTheatresByMovieEntryDto), HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getMoviesByGenre")
    public ResponseEntity getMoviesByGenre(@RequestBody GetMoviesByGenreEntryDto getMoviesByGenreEntryDto) {
        try {
            return new ResponseEntity<>(movieService.getMoviesByGenre(getMoviesByGenreEntryDto), HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
    }

}
