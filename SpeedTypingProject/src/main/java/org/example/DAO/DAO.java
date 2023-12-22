package org.example.DAO;

import org.example.Entities.User;

import java.util.List;

public interface DAO <T, E>{
    public void save(T t);

    void delete(T t);

    T getById(E id);

    List<T> getAll();

    void update(T t);
}
