package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.ShowEntryDtoToShowEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.ShowEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Models.*;
import com.example.BackendDev.BookMyShow.Repository.MovieRepository;
import com.example.BackendDev.BookMyShow.Repository.ShowRepository;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public String addShow(ShowEntryDto showEntryDto) throws Exception{

        int movieId = showEntryDto.getMovieId();
        int theatreId = showEntryDto.getTheatreId();

        Movie movie = movieRepository.findById(movieId).get();
        Theatre theatre = theatreRepository.findById(theatreId).get();


        Show show = ShowEntryDtoToShowEntity.EntryDtoToShow(showEntryDto, movie, theatre);   // Converting showEntryDto to Show Entity.
        List<ShowSeat> showSeatList = createShowSeatList(showEntryDto, show, theatre);       // Have to create ShowSeat list to add in the Show.
        show.setShowSeatList(showSeatList);


        movie.getTheatreList().add(theatre);        // Adding the theatre to theatre list and show to showList in the Movie Entity.
        movie.getShowList().add(show);


        theatre.getListOfShowsInATheatre().add(show);       // setting the attributes of theatre entity by adding show to its show list.
        theatre.setMovie(movie);


        movieRepository.save(movie);            // Movie is the parent to both theatre and show. Therefore, adding theatre to the db will automatically add theatre and show.

        return "show created";
    }

    public List<ShowSeat> createShowSeatList(ShowEntryDto showEntryDto, Show show, Theatre theatre) {

        int reclinerPrice = showEntryDto.getReclinerPrice();
        int classicPrice = showEntryDto.getClassicPrice();

        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();
        List<ShowSeat> showSeatList = new ArrayList<>();

        for (TheatreSeat theatreSeat: theatreSeatList) {
            ShowSeat showSeat = ShowSeat.builder()
                                .seatNo(theatreSeat.getSeatNo())
                                .seatType(theatreSeat.getSeatType())
                                .show(show).isBooked(false).build();

            if (theatreSeat.getSeatType().equals(SeatType.RECLINER)) showSeat.setPrice(reclinerPrice);
            else showSeat.setPrice(classicPrice);
            showSeatList.add(showSeat);
        }

        return showSeatList;
    }


}
