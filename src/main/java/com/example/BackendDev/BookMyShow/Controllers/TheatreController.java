package com.example.BackendDev.BookMyShow.Controllers;

import com.example.BackendDev.BookMyShow.EntryDTOs.TheatreEntryDto;
import com.example.BackendDev.BookMyShow.ResponseDTOs.GetTheatresByLocDto;
import com.example.BackendDev.BookMyShow.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/addTheatre")
    public ResponseEntity addTheatre(@RequestBody TheatreEntryDto theatreEntryDto) {
        try {
            String response = theatreService.addTheatre(theatreEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e) {
            String response = "Failed to add the theatre";
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTheatresByLocation")
    public ResponseEntity getTheatreByLoc(String location) {
        try {
            List<GetTheatresByLocDto> theatreList = theatreService.getTheatresByLoc(location);
            return new ResponseEntity<>(theatreList, HttpStatus.FOUND);
        }
        catch(Exception e) {
            String response = e.getLocalizedMessage();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
