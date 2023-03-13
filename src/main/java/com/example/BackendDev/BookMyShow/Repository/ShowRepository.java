package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.ArrayList;

public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Query(value = "select show_time from shows where movie_id=:movieId and theatre_theatre_id=:theatreId", nativeQuery = true)
    ArrayList<LocalTime> getShowTimings(int theatreId, int movieId);
}
