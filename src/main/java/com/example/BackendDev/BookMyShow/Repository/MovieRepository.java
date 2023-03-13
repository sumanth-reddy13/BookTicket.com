package com.example.BackendDev.BookMyShow.Repository;
import com.example.BackendDev.BookMyShow.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query(value = "select movie_name, showcount FROM (select movie_name, COUNT(*) AS showcount " +
            "FROM movies AS m LEFT JOIN shows AS s ON m.id = s.movie_id group by movie_id) AS t " +
            "WHERE showcount = (SELECT MAX(countshows) FROM (SELECT movie_id, COUNT(*) AS countshows " +
            "FROM shows group by movie_id) AS showss);", nativeQuery = true)
    List<Object[]> maxShows();

    @Query(value = "select id from movies where movie_name=:movieName", nativeQuery = true)
    int getMovieId(String movieName);

    @Query(value = "select name FROM MTM AS m LEFT JOIN theatres AS t " +
            "ON m.theatre_id = t.theatre_id where movie_id=:movieId", nativeQuery = true)
    List<String> getTheatres(int movieId);

    @Query(value = "select * from movies where genre=:genre", nativeQuery = true)
    List<Object[]> getMoviesByGenre(String genre);

}
