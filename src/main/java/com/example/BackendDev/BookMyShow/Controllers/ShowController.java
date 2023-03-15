package com.example.BackendDev.BookMyShow.Controllers;

import com.example.BackendDev.BookMyShow.EntryDTOs.ShowEntryDto;
import com.example.BackendDev.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("addShow")
    public ResponseEntity addShow(@RequestBody ShowEntryDto showEntryDto) {

        try {
            String response = showService.addShow(showEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("getShowTimings")
    public ResponseEntity getShowTimings(@RequestParam("movieId") int movieId, @RequestParam("theatreId") int theatreId) {
        try {
            ArrayList<LocalTime> list = showService.getShowTimings(movieId, theatreId);
            return new ResponseEntity<>(list, HttpStatus.FOUND);
        }
        catch (Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmptySeats/{showNo}")
    public ResponseEntity getEmptySeats(@PathVariable("showNo") int showNo) {
        try {
            List<String> emptySeats = showService.getEmptySeatsForAShow(showNo);
            return new ResponseEntity<>(emptySeats, HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }




}
