package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Enums.ShowType;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.Models.TheatreSeat;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import com.example.BackendDev.BookMyShow.ResponseDTOs.GetTheatresByLocDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(TheatreEntryDto theatreEntryDto) throws Exception{

        Theatre theatre = new Theatre();
        theatre.setName(theatreEntryDto.getName());
        theatre.setLocation(theatreEntryDto.getLocation());

        int recliner = theatreEntryDto.getRecliner();
        int classic = theatreEntryDto.getClassic();

        // To Single Point Responsibility, I have made a method to create TheatreSeat classes and added to the list
        // The method returns a list of theatreSeats.

        List<TheatreSeat> theatreSeatList = listOfTheatreSeats(recliner, classic, theatre);

        theatre.setTheatreSeatList(theatreSeatList);

        theatreRepository.save(theatre);

        return "theatre added successfully";
    }

    public List<TheatreSeat> listOfTheatreSeats(int recliner, int classic, Theatre theatre) {

        List<TheatreSeat> theatreSeatList = new ArrayList<>();

        for (int i = 1;i <= recliner;i++) {
            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatType(SeatType.RECLINER);
            theatreSeat.setSeatNo(i + "R");
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
        }

        int seatNo = recliner + 1;
        for (int i = 1;i <= classic; i++) {
            TheatreSeat theatreSeat = new TheatreSeat();
            theatreSeat.setSeatType(SeatType.CLASSIC);
            theatreSeat.setSeatNo(i + "C");
            theatreSeat.setTheatre(theatre);
            theatreSeatList.add(theatreSeat);
        }

        return theatreSeatList;
    }

    public List<GetTheatresByLocDto> getTheatresByLoc(String location) {
        List<Object[]> theatreList = theatreRepository.getTheatresByLoc(location);

        List<GetTheatresByLocDto> listOfTheatres = new ArrayList<>();
        for (Object[] a : theatreList) {

            Time t = (Time)a[4];
            Date d = (Date)a[5];

            GetTheatresByLocDto g = GetTheatresByLocDto.builder()
                    .location((String) a[0])
                    .theatreName((String) a[1])
                    .movieName((String) a[2])
                    .showId((int) a[3])
                    .showTime(t.toLocalTime())
                    .showDate(d.toLocalDate())
                    .showType(ShowType.valueOf((String)a[6])).build();

            listOfTheatres.add(g);

        }
        return listOfTheatres;
    }
}
