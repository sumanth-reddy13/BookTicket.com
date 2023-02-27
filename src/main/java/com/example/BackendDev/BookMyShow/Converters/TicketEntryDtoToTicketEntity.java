package com.example.BackendDev.BookMyShow.Converters;

import com.example.BackendDev.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BackendDev.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Models.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public class TicketEntryDtoToTicketEntity {
    static int priceOfEachTicket = 0;
    public static Ticket entryDtoToTicket(TicketEntryDto ticketEntryDto, Movie movie, Theatre theatre, User user, Show show) {

        int noOfTickets = ticketEntryDto.getNoOfTickets();
        SeatType seatType = ticketEntryDto.getSeatType();

        List<ShowSeat> showSeatList = show.getShowSeatList();
        if (!isTicketsAvailable(showSeatList, noOfTickets, seatType)) return null;

        int totalPrice = noOfTickets * priceOfEachTicket;

        Ticket ticket = Ticket.builder()
                                .movieName(movie.getName())
                                .theatreName(theatre.getName())
                                .showDate(show.getShowDate())
                                .showTime(show.getShowTime())
                                .show(show).user(user)
                                .totalAmount(totalPrice).build();

        return ticket;
    }

    public static boolean isTicketsAvailable(List<ShowSeat> showSeatList, int noOfTickets, SeatType seatType) {

        List<ShowSeat> selectedSeats = new ArrayList<>();
        for (ShowSeat showSeat : showSeatList) {
            if (showSeat.getSeatType().equals(seatType) && showSeat.isBooked() == false) {
                priceOfEachTicket = showSeat.getPrice();
                selectedSeats.add(showSeat);
            }
        }

        LocalDate localDate = LocalDate.now();

        if (selectedSeats.size() >= noOfTickets) {
            for (int i = 0;i < noOfTickets;i++) {
                ShowSeat showSeat = selectedSeats.get(i);
                showSeat.setBooked(true);
                showSeat.setBookedAt(localDate);
            }
        }
        else {
            return false;
        }
        return true;
    }
}
