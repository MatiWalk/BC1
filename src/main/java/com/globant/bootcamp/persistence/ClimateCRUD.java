package com.globant.bootcamp.persistence;

/**
 * Created by Mati on 15/05/2017.
 */
public interface ClimateCRUD<T> extends ClimateR<T> {
    int insert(T t);
    void update(T t, int id);
    void deleteByID(int id);
}
