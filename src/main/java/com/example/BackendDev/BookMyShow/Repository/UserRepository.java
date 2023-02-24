package com.example.BackendDev.BookMyShow.Repository;

import com.example.BackendDev.BookMyShow.Models.User;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
