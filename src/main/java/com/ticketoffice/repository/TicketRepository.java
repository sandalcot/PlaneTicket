package com.ticketoffice.repository;

import com.ticketoffice.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public interface TicketRepository extends GenericRepository<Ticket, Integer> {

    List<Ticket> getIdTicketPass(Integer id) throws SQLException, ClassNotFoundException, InterruptedException;

}
