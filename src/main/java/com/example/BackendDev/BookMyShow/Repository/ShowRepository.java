package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "select show_time from shows where movie_id=:movieId and theatre_theatre_id=:theatreId", nativeQuery = true)
    ArrayList<Time> getShowTimings(int theatreId, int movieId);

    @Query(value = "select st.seat_no FROM (select seat_no, is_booked, s.show_id FROM shows AS s JOIN show_seats AS ss ON s.show_id = ss.show_show_id) AS st where st.is_booked = false and st.show_id=:number", nativeQuery = true)
    List<String> getEmptySeats(int number);

}
