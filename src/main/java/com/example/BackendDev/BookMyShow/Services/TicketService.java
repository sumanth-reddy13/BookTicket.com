package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.TicketEntryDtoToTicketEntity;
import com.example.BackendDev.BookMyShow.EntryDTOs.GetNoOfTicketBookedEntryDto;
import com.example.BackendDev.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BackendDev.BookMyShow.Models.*;
import com.example.BackendDev.BookMyShow.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
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

    @Autowired
    JavaMailSender javaMailSender;

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
            if (requestedSeats.contains(showSeat.getSeatNo())) {
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
        ticketEntity.setNoOfTickets(requestedSeats.size());


        User user = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUser(user);

        ticketEntity = ticketRepository.save(ticketEntity);  // saving & getting the ticketEntity beforehand to avoid duplicates
                                                            // while saving user & show Entity, as ticketEntity is a child of both.

        // setting parent entity attributes
        user.getBookedTickets().add(ticketEntity);
        show.getBookedTickets().add(ticketEntity);

        userRepository.save(user);
        showRepository.save(show);

        String message = "This is to confirm your requested seats " +requestedSeats + " are booked successfully for the movie " + ticketEntity.getMovieName();

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("sumanthdamma@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(message);
        mimeMessageHelper.setSubject("Confirmation for the booking");

        javaMailSender.send(mimeMessage);
        System.out.printf("Mail with attachment sent successfully");

        return "ticket booked";
    }

    public boolean validateSeats(List<String> requestedSeats, List<ShowSeat> showSeatList) throws Exception{

        for (ShowSeat showSeat : showSeatList) {
            if (requestedSeats.contains(showSeat.getSeatNo())) {
                if (showSeat.isBooked() == true) {
                    return false;
                }
            }
        }
        return true;
    }

    public Integer ticketsBookedForMovie(GetNoOfTicketBookedEntryDto getNoOfTicketBookedEntryDto) {
        String movieName = getNoOfTicketBookedEntryDto.getMovieName();
        String fromDate = getNoOfTicketBookedEntryDto.getFromDate();
        String toDate = getNoOfTicketBookedEntryDto.getToDate();

//        int movieId = movieRepository.getMovieId(movieName);

        Integer ticketsBooked = ticketRepository.getTicketsBooked(movieName, fromDate, toDate);
        if (ticketsBooked == null) return 0;
        return ticketsBooked;
    }

}


