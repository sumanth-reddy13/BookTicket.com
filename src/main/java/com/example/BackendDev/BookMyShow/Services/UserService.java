package com.example.BackendDev.BookMyShow.Services;

import com.example.BackendDev.BookMyShow.Converters.UserEntryDtoToUser;
import com.example.BackendDev.BookMyShow.EntryDTOs.UserEntryDto;
import com.example.BackendDev.BookMyShow.Models.User;
import com.example.BackendDev.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto) throws Exception{

        // created a DtoToUser Converter and made that method public to call without an instance/object
        User user = UserEntryDtoToUser.DtoToUserEntity(userEntryDto);
        userRepository.save(user);

        return "user added to the database";
    }
}
