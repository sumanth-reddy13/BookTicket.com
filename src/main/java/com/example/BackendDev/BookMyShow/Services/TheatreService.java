package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.Models.TheatreSeat;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
