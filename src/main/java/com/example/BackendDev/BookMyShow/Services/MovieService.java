package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.MovieEntryDtoToMovieEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.MovieEntryDto;
import com.example.BackendDev.BookMyShow.Models.Movie;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.Repository.MovieRepository;
import com.example.BackendDev.BookMyShow.ResponseDTOs.GetMaxShowsDto;
import com.example.BackendDev.BookMyShow.ResponseDTOs.GetMoviesByGenreResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) throws Exception{

        Movie movie = MovieEntryDtoToMovieEntity.EntryDtoToMovie(movieEntryDto);
        movieRepository.save(movie);
        return "movie added";
    }

    public List<GetMaxShowsDto> getMovieWithMaxShows() {
        List<Object[]> list = movieRepository.maxShows();
        List<GetMaxShowsDto> showsDtos = new ArrayList<>();

        for (Object[] a : list) {
            GetMaxShowsDto g = new GetMaxShowsDto();
            g.setMovie_name((String) a[0]);
            g.setNoOfShows((BigInteger) a[1]);
            showsDtos.add(g);
        }

        return showsDtos;
    }

    public List<String> getTheatresOfAMovie(String movieName) {
        int movieId = movieRepository.getMovieId(movieName);
        Movie movie = movieRepository.findById(movieId).get();

        List<Theatre> theatreList = movie.getTheatreList();
        List<String> theatres = new ArrayList<>();

        for (Theatre theatre : theatreList) {
            String name = theatre.getName();
            theatres.add(name);
        }

        return theatres;
    }

    public List<GetMoviesByGenreResponseDto> getMoviesByGenre(String genre) {

        List<Object[]>  list = movieRepository.getMoviesByGenre(genre);
        List<GetMoviesByGenreResponseDto> responseDtoList = new ArrayList<>();

        for (Object[] arr : list) {
            GetMoviesByGenreResponseDto g = GetMoviesByGenreResponseDto.builder()
                    .id((int) arr[0]).duration((int) arr[1])
                    .genre((String) arr[2]).language((String) arr[3])
                    .name((String) arr[4]).ratings((double) arr[5]).build();

            responseDtoList.add(g);
        }

        return responseDtoList;
    }
}
