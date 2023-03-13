package com.example.BackendDev.BookMyShow.Converters;

import com.example.BackendDev.BookMyShow.EntryDTOs.ShowEntryDto;
import com.example.BackendDev.BookMyShow.Enums.ShowType;
import com.example.BackendDev.BookMyShow.Models.Movie;
import com.example.BackendDev.BookMyShow.Models.Show;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.Repository.MovieRepository;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowEntryDtoToShowEntity {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public static Show EntryDtoToShow(ShowEntryDto showEntryDto, Movie movie, Theatre theatre){

//        String time = showEntryDto.getShowTime();
//        int hour = Integer.valueOf(time.substring(0,2));
//        int minute = Integer.valueOf(time.substring(3));
//
//        String date = showEntryDto.getShowDate();
//        String[] d = date.split("/");
//        int year = Integer.valueOf(d[0]);
//        int month = Integer.valueOf(d[1]);
//        int day = Integer.valueOf(d[2]);

        Show show = Show.builder().showTime(showEntryDto.getShowTime())
                    .showDate(showEntryDto.getShowDate())
                    .showType(showEntryDto.getShowType())
                    .movie(movie).theatre(theatre).build();

//        Show show = Show.builder().showDate(LocalDate.of(year, month, day))
//                .showTime(LocalTime.of(hour, minute)).showType(showEntryDto.getShowType())
//                .movie(movie).theatre(theatre).build();

        return show;
    }
}
