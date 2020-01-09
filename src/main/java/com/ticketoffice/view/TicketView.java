package com.ticketoffice.view;

import com.ticketoffice.controller.TicketController;
import com.ticketoffice.model.Ticket;
import com.ticketoffice.model.TypeSeat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketView {
    public static final String ACTIONS_ROUTE = "Введите действие:\n" +
            "1.Создать новый билет\n" +
            "2.Изменить билет\n" +
            "3.Возврат билета\n" +
            "4.Найти билет по айди\n" +
            "5.Найти билет по айди пассажира\n" +
            "6.Список всех билетов\n";

    TicketController ticketController = new TicketController();

    public void createTicket() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дату:");
        String date = scanner.next();
        System.out.println("Введите тип места:");
        TypeSeat type = TypeSeat.valueOf(scanner.next());
        System.out.println("Введите цену:");
        int price = Integer.parseInt(scanner.next());
        System.out.println("Введите айди самолета:");
        int idPlane = Integer.parseInt(scanner.next());
        System.out.println("Введите айди пассажира:");
        int idPass = Integer.parseInt(scanner.next());
        System.out.println("Введите айди маршрута:");
        int idRoutes = Integer.parseInt(scanner.next());
        Ticket ticket = new Ticket(date,type,price,idPlane,idPass,idRoutes);
        ticketController.createTicket(ticket);
    }

    public void updateTicket() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди билета для его изменения:");
        int index = Integer.parseInt(scanner.next());
        System.out.println("Введите дату:");
        String date = scanner.next();
        System.out.println("Введите тип места:");
        TypeSeat type = TypeSeat.valueOf(scanner.next());
        System.out.println("Введите цену:");
        int price = Integer.parseInt(scanner.next());
        System.out.println("Введите айди самолета:");
        int idPlane = Integer.parseInt(scanner.next());
        System.out.println("Введите айди пассажира:");
        int idPass = Integer.parseInt(scanner.next());
        System.out.println("Введите айди маршрута:");
        int idRoutes = Integer.parseInt(scanner.next());
        Ticket ticket = new Ticket(index,date,type,price,idPlane,idPass,idRoutes);
        ticketController.updateTicket(ticket);
    }

    public void deleteTicket() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди билета для его удаления:");
        int index = Integer.parseInt(scanner.next());
        ticketController.deleteTicket(index);
    }

    public void getIdTicket() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди билета для поиска:");
        int index = Integer.parseInt(scanner.next());
        System.out.println(ticketController.getIdTicket(index));
    }

    public void getIdByPass() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди пассажира для поиска:");
        int index = Integer.parseInt(scanner.next());
        List<Ticket> tickets = ticketController.getIdTicketPass(index);
        for(Ticket ticket:tickets){
            System.out.println(ticket);
        }
    }

    public void printTicket() throws Exception {
        System.out.println("Список всех билетов:");
        for (Ticket item : ticketController.getAllTicket()) {
            System.out.println(item);
        }
    }
}
