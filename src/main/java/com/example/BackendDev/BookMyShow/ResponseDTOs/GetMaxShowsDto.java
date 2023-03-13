package com.example.BackendDev.BookMyShow.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMaxShowsDto {
    private String movie_name;
    private BigInteger noOfShows;

}
