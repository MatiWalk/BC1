package dao;

import java.util.List;

/**
 * Created by Mati on 15/05/2017.
 */
public interface ClimateDAO<T> {
    int insert(T t);
    void update(T t, int id);
    void deleteByID(int id);
    T selectByID(int id);
    List<T> selectAll();
}
