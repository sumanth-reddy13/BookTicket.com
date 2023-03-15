package com.example.BackendDev.BookMyShow.Controllers;

import com.example.BackendDev.BookMyShow.EntryDTOs.UserEntryDto;
import com.example.BackendDev.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserEntryDto userEntryDto) {

        try {
            String response = userService.addUser(userEntryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch(Exception e) {
            String response = "failed to add the user";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
