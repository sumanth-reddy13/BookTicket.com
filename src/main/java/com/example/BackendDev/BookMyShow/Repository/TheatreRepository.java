package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

    @Query(value = "select location, name, movie_name, show_id, show_time, show_date, show_type" +
            " FROM (select t.location, t.name, s.movie_id, s.show_id, s.show_date, s.show_time, s.show_type" +
            " FROM theatres AS t JOIN shows AS s ON t.theatre_id = s.theatre_theatre_id) AS th LEFT JOIN movies AS m" +
            " ON th.movie_id = m.id where location=:location", nativeQuery = true)
    List<Object[]> getTheatresByLoc(String location);
}
