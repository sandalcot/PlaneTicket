package com.ticketoffice.view;

import com.ticketoffice.controller.RouteController;
import com.ticketoffice.model.Routes;

import java.util.Scanner;

public class RoutesView {
    public static final String ACTIONS_ROUTE = "Введите действие:\n" +
            "1.Зарегистрировать новый маршрут\n" +
            "2.Изменить маршрут\n" +
            "3.Удалить маршрут\n" +
            "4.Получить информацию о маршруте по айди\n" +
            "5.Список всех маршрутов\n";

    RouteController routeController = new RouteController();

    public void createRouter() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите город отправления:");
        String departure = scanner.next();
        System.out.println("Введите город прибывания:");
        String arrival = scanner.next();
        Routes routes = new Routes(departure,arrival);
        routeController.createRoute(routes);
    }

    public void updateRoute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди маршрута для его изменения:");
        int index = Integer.parseInt(scanner.next());
        System.out.println("Введите город отправления:");
        String departure = scanner.next();
        System.out.println("Введите город прибывания:");
        String arrival = scanner.next();
        Routes routes = new Routes(index, departure, arrival);
        routeController.updateRoute(routes);
    }

    public void deleteRoute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди маршрута для его удаления:");
        int index = Integer.parseInt(scanner.next());
        routeController.deleteRoute(index);
    }

    public void getIdRoute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди маршрута:");
        int index = Integer.parseInt(scanner.next());
        System.out.println(routeController.getIdRoute(index));
    }

    public void printRoute() throws Exception {
        System.out.println("Список всех маршрутов:");
        for (Routes item : routeController.getAllRoute()) {
            System.out.println(item);
        }
    }
}
