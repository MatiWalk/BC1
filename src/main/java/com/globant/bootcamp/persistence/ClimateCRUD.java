package com.globant.bootcamp.persistence;

/**
 * Created by Mati on 15/05/2017.
 */
public interface ClimateCRUD<T> extends ClimateR<T> {
    int insert(T t);
    boolean update(T t);
    void deleteByID(int id);
}
