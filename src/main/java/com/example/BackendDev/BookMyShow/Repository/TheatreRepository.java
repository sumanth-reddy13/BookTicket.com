package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
}
