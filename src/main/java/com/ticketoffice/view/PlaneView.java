package com.ticketoffice.view;

import com.ticketoffice.controller.PlaneController;
import com.ticketoffice.model.Plane;

import java.util.Scanner;

public class PlaneView {
    public static final String ACTIONS_PLANE = "Введите действие:\n" +
            "1.Зарегистрировать новый самолет\n" +
            "2.Изменить самолет\n" +
            "3.Удалить самолет\n" +
            "4.Получить информацию о самолете по айди\n" +
            "5.Список всех самолетов\n";

    PlaneController planeController = new PlaneController();

    public void createPlane() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите модель самолета:");
        String name = scanner.next();
        Plane plane = new Plane(name);
        planeController.createPlane(plane);
    }

    public void updatePlane() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди самолета для его изменения:");
        int index = Integer.parseInt(scanner.next());
        System.out.println("Введите наименование самолета:");
        String name = scanner.next();
        Plane plane = new Plane(index, name);
        planeController.updatePlane(plane);
    }

    public void deletePlane() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди самолета для его удаления:");
        int index = Integer.parseInt(scanner.next());
        planeController.deletePlane(index);
    }

    public void getIdPlane() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди самолета:");
        int index = Integer.parseInt(scanner.next());
        System.out.println(planeController.getIdPlane(index));
    }

    public void printPlane() throws Exception {
        System.out.println("Список всех самолетов:");
        for (Plane item : planeController.getAllPlane()) {
            System.out.println(item);
        }
    }
}
