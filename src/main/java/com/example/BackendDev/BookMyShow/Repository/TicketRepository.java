package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "select SUM(no_of_tickets) AS tickets_Sold FROM ticket " +
            "WHERE movie_name=:movieName AND show_date BETWEEN :fromDate AND :toDate", nativeQuery = true)
    Integer getTicketsBooked(String movieName, String fromDate, String toDate);
}
