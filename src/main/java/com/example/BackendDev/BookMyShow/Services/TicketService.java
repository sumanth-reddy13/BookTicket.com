package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.TicketEntryDtoToTicketEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BackendDev.BookMyShow.Models.*;
import com.example.BackendDev.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    public String bookTicket(TicketEntryDto ticketEntryDto) throws Exception{

//    step 1 -> Convert entryDto ---- Entity
        Ticket ticketEntity = TicketEntryDtoToTicketEntity.entryDtoToTicket(ticketEntryDto);

        // Do ticket validation.
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();
        int showId = ticketEntryDto.getShowId();

        Show show = showRepository.findById(showId).get();
        List<ShowSeat> showSeatList = show.getShowSeatList();

        if (!validateSeats(requestedSeats, showSeatList)) {
            throw new Exception("The seat is already booked. Please select other seats");
        }

        int totalPrice = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestedSeats.contains(showSeat)) {
                totalPrice += showSeat.getPrice();
                showSeat.setBookedAt(new Date());
                showSeat.setBooked(true);
            }
        }

        ticketEntity.setTotalAmount(totalPrice);
        ticketEntity.setShow(show);
        ticketEntity.setMovieName(show.getMovie().getName());
        ticketEntity.setTheatreName(show.getTheatre().getName());
        ticketEntity.setShowDate(show.getShowDate());
        ticketEntity.setShowTime(show.getShowTime());


        User user = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUser(user);

        // setting parent entity attributes
        user.getBookedTickets().add(ticketEntity);
        show.getBookedTickets().add(ticketEntity);

        userRepository.save(user);
        showRepository.save(show);

        return "ticket booked";
    }

    public boolean validateSeats(List<String> requestedSeats, List<ShowSeat> showSeatList) throws Exception{

        for (ShowSeat showSeat : showSeatList) {
            if (requestedSeats.contains(showSeat)) {
                if (showSeat.isBooked()) {
                    return false;
                }
            }
        }
        return true;
    }

}

/*
//        int movieId = ticketEntryDto.getMovieId();
//        int theatreId = ticketEntryDto.getTheatreId();
//        int userId = ticketEntryDto.getUserId();
//        int showId = ticketEntryDto.getShowId();
//
//        System.out.println("movieId " + movieId + "theatreId " + theatreId + "userId" + userId + "showId " + showId);
//
//        Movie movie = movieRepository.findById(movieId).get();
//        Theatre theatre = theatreRepository.findById(theatreId).get();
//        User user = userRepository.findById(userId).get();
//        Show show = showRepository.findById(showId).get();
//
//        Ticket ticket = TicketEntryDtoToTicketEntity.entryDtoToTicket(ticketEntryDto, movie, theatre, user, show);
//
//        if (ticket == null) {
//            return "required no of seats are not present at the moment";
//        }
//
//        user.getBookedTickets().add(ticket);
//        show.getBookedTickets().add(ticket);
//
//        ticketRepository.save(ticket);
 */
