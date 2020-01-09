package com.ticketoffice.controller;

import com.ticketoffice.model.Plane;
import com.ticketoffice.repository.JavaJDBCPlaneRepositoryImpl;
import com.ticketoffice.repository.PlaneRepository;

import java.util.List;

public class PlaneController {
    PlaneRepository planeRepository = new JavaJDBCPlaneRepositoryImpl();

    public void createPlane(Plane plane) throws Exception {
        planeRepository.create(plane);
    }

    public void updatePlane(Plane plane) throws Exception {
        planeRepository.update(plane);
    }

    public void deletePlane(int id) throws Exception {
        planeRepository.delete(id);
    }

    public List<Plane> getAllPlane() throws Exception {
        return planeRepository.getAll();
    }

    public Plane getIdPlane(int id) throws Exception {
        return planeRepository.getId(id);
    }
}
