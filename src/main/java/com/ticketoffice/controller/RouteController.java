package com.ticketoffice.controller;

import com.ticketoffice.model.Routes;
import com.ticketoffice.jdbc.JavaJDBCRoutesRepositoryImpl;
import com.ticketoffice.repository.RoutesRepository;

import java.util.List;

public class RouteController {
    RoutesRepository routesRepository = new JavaJDBCRoutesRepositoryImpl();

    public void createRoute(Routes routes) throws Exception {
        routesRepository.create(routes);
    }

    public void updateRoute(Routes routes) throws Exception {
        routesRepository.update(routes);
    }

    public void deleteRoute(int id) throws Exception {
        routesRepository.delete(id);
    }

    public List<Routes> getAllRoute() throws Exception {
        return routesRepository.getAll();
    }

    public Routes getIdRoute(int id) throws Exception {
        return routesRepository.getId(id);
    }
}
