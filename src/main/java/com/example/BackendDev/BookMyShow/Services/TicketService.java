package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.TicketEntryDtoToTicketEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BackendDev.BookMyShow.Models.*;
import com.example.BackendDev.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;

    public String bookTicket(TicketEntryDto ticketEntryDto) {

        int movieId = ticketEntryDto.getMovieId();
        int theatreId = ticketEntryDto.getTheatreId();
        int userId = ticketEntryDto.getUserId();
        int showId = ticketEntryDto.getShowId();

        System.out.println("movieId " + movieId + "theatreId " + theatreId + "userId" + userId + "showId " + showId);

        Movie movie = movieRepository.findById(movieId).get();
        Theatre theatre = theatreRepository.findById(theatreId).get();
        User user = userRepository.findById(userId).get();
        Show show = showRepository.findById(showId).get();

        Ticket ticket = TicketEntryDtoToTicketEntity.entryDtoToTicket(ticketEntryDto, movie, theatre, user, show);

        if (ticket == null) {
            return "required no of seats are not present at the moment";
        }

        user.getBookedTickets().add(ticket);
        show.getBookedTickets().add(ticket);

        ticketRepository.save(ticket);

        return "ticket booked";
    }
}
