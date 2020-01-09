package com.ticketoffice.controller;

import com.ticketoffice.model.Ticket;
import com.ticketoffice.repository.JavaJDBCTicketRepositoryImpl;
import com.ticketoffice.repository.TicketRepository;

import java.util.List;

public class TicketController {
    TicketRepository ticketRepository = new JavaJDBCTicketRepositoryImpl();

    public void createTicket(Ticket ticket) throws Exception {
        ticketRepository.create(ticket);
    }

    public void updateTicket(Ticket ticket) throws Exception {
        ticketRepository.update(ticket);
    }

    public void deleteTicket(int id) throws Exception {
        ticketRepository.delete(id);
    }

    public List<Ticket> getAllTicket() throws Exception {
        return ticketRepository.getAll();
    }

    public Ticket getIdTicket(int id) throws Exception {
        return ticketRepository.getId(id);
    }

    public List<Ticket> getIdTicketPass(int id) throws Exception {
        return ticketRepository.getIdTicketPass(id);
    }


}
