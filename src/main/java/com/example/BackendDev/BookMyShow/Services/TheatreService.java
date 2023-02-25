package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BackendDev.BookMyShow.Enums.SeatType;
import com.example.BackendDev.BookMyShow.Models.Theatre;
import com.example.BackendDev.BookMyShow.Models.TheatreSeat;
import com.example.BackendDev.BookMyShow.Models.TheatreSeatList;
import com.example.BackendDev.BookMyShow.Repository.TheatreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    public String addTheatre(TheatreEntryDto theatreEntryDto) {

        // created a theatre object.
        Theatre theatre =   Theatre.builder()
                            .name(theatreEntryDto.getName())
                            .location(theatreEntryDto.getLocation()).build();


        int recliner = theatreEntryDto.getRecliner();
        int classic = theatreEntryDto.getClassic();
        List<TheatreSeat> theatreSeatsRow = new ArrayList<>();

//        TheatreSeat theatreSeat = new TheatreSeat();
//        List<TheatreSeatList> list = new ArrayList<>();

//
//        TheatreSeatList theatreSeatList = new TheatreSeatList();
//        theatreSeatList.setSeatNo(1);
//        theatreSeatList.setSeatType(SeatType.RECLINER);
//        theatreSeatList.setTheatreSeat(theatreSeat);
//        list.add(theatreSeatList);
//
//        theatreSeat.setTheatreSeatListRow(list);
//        theatreSeat.setTheatre(theatre);
//        theatreSeat.setRowNo(1);
//        theatreSeat.setSeatType(SeatType.RECLINER);
//        theatreSeatsRow.add(theatreSeat);
//
//        theatre.setListOfTheatreSeats(theatreSeatsRow);

        int rowNo = 1;
        int seatNo = 1;

        int i = 1;
        while (i <= recliner) {

            TheatreSeat theatreSeat = new TheatreSeat();
            List<TheatreSeatList> seatList = new ArrayList<>();


            for (; i <= recliner; i++) {
                TheatreSeatList theatreSeatList = new TheatreSeatList();
                theatreSeatList.setSeatType(SeatType.RECLINER);
                theatreSeatList.setSeatNo(seatNo);
                theatreSeatList.setTheatreSeat(theatreSeat);
                seatList.add(theatreSeatList);
                seatNo++;
                if (i % 10 == 0) {
                    break;
                }
            }
            theatreSeat.setTheatre(theatre);
            theatreSeat.setSeatType(SeatType.RECLINER);
            theatreSeat.setRowNo(rowNo);
            theatreSeat.setTheatreSeatListRow(seatList);
            theatreSeatsRow.add(theatreSeat);
            rowNo++;
        }
        i = 1;
        while (i <= classic) {

            TheatreSeat theatreSeat = new TheatreSeat();
            List<TheatreSeatList> seatList = new ArrayList<>();
//            int seatNo = 1;

            for (; i <= classic; i++) {
                TheatreSeatList theatreSeatList = new TheatreSeatList();
                theatreSeatList.setSeatType(SeatType.CLASSIC);
                theatreSeatList.setSeatNo(seatNo);
                theatreSeatList.setTheatreSeat(theatreSeat);
                seatList.add(theatreSeatList);
                seatNo++;
                if (i % 10 == 0) {
                    break;
                }
            }
            theatreSeat.setTheatre(theatre);
            theatreSeat.setSeatType(SeatType.CLASSIC);
            theatreSeat.setRowNo(rowNo);
            theatreSeat.setTheatreSeatListRow(seatList);
            theatreSeatsRow.add(theatreSeat);
            rowNo++;
        }
        theatre.setListOfTheatreSeats(theatreSeatsRow);

        theatreRepository.save(theatre);

        return "theatre added";
    }

}
