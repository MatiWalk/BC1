package dao;

import java.util.List;

/**
 * Created by Mati on 15/05/2017.
 */
public interface weatherDAO<T> {
    int insert(T t);
    void updateByObject(T t);
    void deleteByObject(T t);
    T selectByID(int i);
    List<T> selectAll();
}
