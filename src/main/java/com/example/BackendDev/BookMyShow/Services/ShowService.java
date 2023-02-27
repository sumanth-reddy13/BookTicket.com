package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.ShowEntryDtoToShowEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.ShowEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Enums.ShowType;
import com.example.BackendDev.BookMyShow.Models.*;
import com.example.BackendDev.BookMyShow.Repository.MovieRepository;
import com.example.BackendDev.BookMyShow.Repository.ShowRepository;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

        // Converting showEntryDto to Show Entity.
        Show show = ShowEntryDtoToShowEntity.EntryDtoToShow(showEntryDto, movie, theatre);

        // Have to create ShowSeat list to add in the Show.
        int ticketPrice = showEntryDto.getTicketPrice();
        List<TheatreSeat> theatreSeatList = theatre.getTheatreSeatList();
        List<ShowSeat> showSeatList = createShowSeatList(theatreSeatList, show, ticketPrice);

        show.setShowSeatList(showSeatList);

        // Adding the theatre to theatre list and show to showList in the Movie Entity.
        movie.getTheatreList().add(theatre);
        movie.getShowList().add(show);

        // setting the attributes of theatre entity by adding show to its show list.
        theatre.getListOfShowsInATheatre().add(show);
        theatre.setMovie(movie);

        // Movie is the parent to both theatre and show. Therefore, adding theatre to the db will automatically add theatre and show.
        movieRepository.save(movie);

        return "show created";
    }

    public List<ShowSeat> createShowSeatList(List<TheatreSeat> theatreSeatList, Show show, int price) {

        List<ShowSeat> showSeatList = new ArrayList<>();

        int reclinerPrice = price + 50;
        for (TheatreSeat theatreSeat: theatreSeatList) {
            ShowSeat showSeat = ShowSeat.builder()
                                .seatNo(theatreSeat.getSeatNo())
                                .seatType(theatreSeat.getSeatType())
                                .show(show).isBooked(false).build();

            if (theatreSeat.getSeatType().equals(SeatType.RECLINER)) showSeat.setPrice(reclinerPrice);
            else showSeat.setPrice(price);
            showSeatList.add(showSeat);
        }
        return showSeatList;
    }

}
