package com.ticketoffice.view;

import java.io.IOException;
import java.util.Scanner;

import static com.ticketoffice.view.PassengerView.ACTIONS_PASS;
import static com.ticketoffice.view.PlaneView.ACTIONS_PLANE;
import static com.ticketoffice.view.RoutesView.ACTIONS_ROUTE;

public class ConsoleHelper {
    private static final String WELCOME_MESSAGE = "Добро пожаловать!\n" +
            "Выберите раздел:\n" +
            "1.Пассажиры\n" +
            "2.Самолеты\n" +
            "3.Маршруты\n" +
            "4.Билеты\n";

    public void run() throws Exception {
        System.out.println(WELCOME_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        switch (line) {
            case "1":
                PassengerView passengerView = new PassengerView();
                System.out.println(ACTIONS_PASS);
                String actionPass = scanner.next();
                switch (actionPass) {
                    case "1":
                        passengerView.createPass();
                        break;
                    case "2":
                        passengerView.updatePass();
                        break;
                    case "3":
                        passengerView.deletePass();
                        break;
                    case "4":
                        passengerView.getIdPass();
                        break;
                    case "5":
                        passengerView.printPass();
                        break;
                }
                break;
            case "2":
                PlaneView planeView = new PlaneView();
                System.out.println(ACTIONS_PLANE);
                String actionPlane = scanner.next();
                switch (actionPlane) {
                    case "1":
                        planeView.createPlane();
                        break;
                    case "2":
                        planeView.updatePlane();
                        break;
                    case "3":
                        planeView.deletePlane();
                        break;
                    case "4":
                        planeView.getIdPlane();
                        break;
                    case "5":
                        planeView.printPlane();
                        break;
                }
                break;
            case "3":
                RoutesView routesView = new RoutesView();
                System.out.println(ACTIONS_ROUTE);
                String actionRoute = scanner.next();
                switch (actionRoute) {
                    case "1":
                        routesView.createRouter();
                        break;
                    case "2":
                        routesView.updateRoute();
                        break;
                    case "3":
                        routesView.deleteRoute();
                        break;
                    case "4":
                        routesView.getIdRoute();
                        break;
                    case "5":
                        routesView.printRoute();
                        break;
                }
                break;
            case "4":
                TicketView ticketView = new TicketView();
                System.out.println(ACTIONS_ROUTE);
                String actionTicket = scanner.next();
                switch (actionTicket) {
                    case "1":
                        ticketView.createTicket();
                        break;
                    case "2":
                        ticketView.updateTicket();
                        break;
                    case "3":
                        ticketView.deleteTicket();
                        break;
                    case "4":
                        ticketView.getIdTicket();
                        break;
                    case "5":
                        ticketView.getIdByPass();
                        break;
                    case "6":
                        ticketView.printTicket();
                        break;
                }
                break;
        }
    }
}
