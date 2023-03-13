package com.example.BackendDev.BookMyShow.Converters;
import com.example.BackendDev.BookMyShow.EntryDTOs.TicketEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Models.*;


public class TicketEntryDtoToTicketEntity {

    public static Ticket entryDtoToTicket(TicketEntryDto ticketEntryDto) {
        Ticket ticket = new Ticket();
        return ticket;
    }

//    public static boolean isTicketsAvailable(List<ShowSeat> showSeatList, int noOfTickets, SeatType seatType) {
//
//        List<ShowSeat> selectedSeats = new ArrayList<>();
//
//    }
}
