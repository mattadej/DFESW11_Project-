package com.qa.employee_manager.service;

import java.util.List;

public interface ServiceMethods<T> {

    //Create
    T create(T employee);

    //Read All
    List<T> readAll();

    //Read By ID
    T readById(long id);

    //Update
    T update(long id, T employee);

    //Delete
    boolean delete(long id);

}
