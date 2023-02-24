package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.EntryDTOs.UserEntryDto;
import com.example.BackendDev.BookMyShow.Models.User;
import com.example.BackendDev.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) {


        // use of builder Annotation to create userEntity.
        User user = User.builder()
                    .name(userEntryDto.getName())
                    .mobile(userEntryDto.getMobile())
                    .email(userEntryDto.getEmail()).build();

        userRepository.save(user);
        return "user added to the database";
    }
}
