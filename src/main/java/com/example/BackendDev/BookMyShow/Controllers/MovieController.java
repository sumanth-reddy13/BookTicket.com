package com.example.BackendDev.BookMyShow.Controllers;

import com.example.BackendDev.BookMyShow.EntryDTOs.MovieEntryDto;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.ResponseDTOs.GetMoviesByGenreResponseDto;
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
    public ResponseEntity getTheatresByMovie(@RequestParam("movieName") String movieName) {
        try {
            List<String> theatreList = movieService.getTheatresOfAMovie(movieName);
            return new ResponseEntity<>(theatreList, HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getMoviesByGenre")
    public ResponseEntity getMoviesByGenre(@RequestParam("genre") String genre) {
        try {
            List<GetMoviesByGenreResponseDto> moviesList = movieService.getMoviesByGenre(genre);
            return new ResponseEntity<>(moviesList, HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }
    }

}
