package com.globant.bootcamp.persistence;

import java.util.List;

/**
 * Created by Sistemas on 18/5/2017.
 */
public interface ClimateR<T> {
    T selectByID(int id);
    List<T> selectAll();
    T selectByObject(T t);
}
