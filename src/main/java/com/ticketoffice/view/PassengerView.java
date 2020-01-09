package com.ticketoffice.view;

import com.ticketoffice.controller.PassController;
import com.ticketoffice.model.Passenger;
import com.ticketoffice.model.Ticket;

import java.io.IOException;
import java.util.Scanner;

public class PassengerView {
    public static final String ACTIONS_PASS = "Введите действие:\n" +
            "1.Зарегистрировать нового пассажира\n" +
            "2.Изменить пассажира\n" +
            "3.Удалить пассажира\n" +
            "4.Получить информацию о пассажире по айди\n" +
            "5.Список всех пассажиров\n";

    PassController passController = new PassController();

    public void createPass() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пассажира:");
        String name = scanner.next();
        System.out.println("Введите фамилию пассажира:");
        String surname = scanner.next();
        System.out.println("Введите дату рождения(в формате:2019-01-29):");
        String birthdate = scanner.next();
        Passenger passenger = new Passenger(name, surname, birthdate);
        passController.createPass(passenger);
    }

    public void updatePass() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди пассажира для его изменения:");
        int index = Integer.parseInt(scanner.next());
        System.out.println("Введите имя пассажира:");
        String name = scanner.next();
        System.out.println("Введите фамилию пассажира:");
        String surname = scanner.next();
        System.out.println("Введите дату рождения(в формате:2019-01-29):");
        String birthdate = scanner.next();
        Passenger passenger = new Passenger(index, name, surname, birthdate);
        passController.updatePass(passenger);
    }

    public void deletePass() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди пассажира для его удаления:");
        int index = Integer.parseInt(scanner.next());
        passController.deletePass(index);
    }

    public void getIdPass() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди пассажира:");
        int index = Integer.parseInt(scanner.next());
        System.out.println(passController.getIdPass(index));
    }

    public void printPass() throws Exception {
        System.out.println("Список всех пассажиров:");
        for (Passenger item : passController.getAllPass()) {
            System.out.println(item);
        }
    }
}
