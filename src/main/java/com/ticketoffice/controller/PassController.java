package com.ticketoffice.controller;

import com.ticketoffice.model.Passenger;
import com.ticketoffice.repository.jdbc.JavaJDBCPassRepositoryImpl;
import com.ticketoffice.repository.PassengerRepository;

import java.util.List;

public class PassController {
    PassengerRepository passengerRepository = new JavaJDBCPassRepositoryImpl();

    public void createPass(Passenger passenger) throws Exception {
        passengerRepository.create(passenger);
    }

    public void updatePass(Passenger passenger) throws Exception {
        passengerRepository.update(passenger);
    }

    public void deletePass(int id) throws Exception {
        passengerRepository.delete(id);
    }

    public List<Passenger> getAllPass() throws Exception {
        return passengerRepository.getAll();
    }

    public Passenger getIdPass(int id) throws Exception {
        return passengerRepository.getId(id);
    }
}
