package com.example.BackendDev.BookMyShow.Converters;

import com.example.BackendDev.BookMyShow.EntryDTOs.UserEntryDto;
import com.example.BackendDev.BookMyShow.Models.User;

public class UserEntryDtoToUser {

    //  made the method public to call the method without an instance/object. Calling without object is more
    //  efficient than calling with object.

    public static User DtoToUserEntity(UserEntryDto userEntryDto) {

        User user = User.builder()
                .name(userEntryDto.getName())
                .mobile(userEntryDto.getMobile())
                .email(userEntryDto.getEmail())
                .address(userEntryDto.getAddress()).build();

        return user;
    }

}
