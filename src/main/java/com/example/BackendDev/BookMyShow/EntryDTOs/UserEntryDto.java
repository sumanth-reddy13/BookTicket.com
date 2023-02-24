package com.example.BackendDev.BookMyShow.EntryDTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class UserEntryDto {

    private String name;
    private String mobile;
    private String email;

}
