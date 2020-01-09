package com.ticketoffice.repository;

import java.io.IOException;
import java.util.List;

public interface GenericRepository <T, ID> {

    void create(T value) throws Exception, IOException;

    void update(T value) throws Exception, IOException;

    void delete(ID id) throws Exception, IOException;

    List<T> getAll() throws Exception, IOException;

    T getId(ID id) throws Exception, IOException;
}
