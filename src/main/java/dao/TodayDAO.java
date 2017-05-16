package dao;

import model.Today;

import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class TodayDAO implements ClimateDAO<Today> {
    @Override
    public int insert(Today today) {
        return 0;
    }

    @Override
    public void update(Today today, int id) {

    }

    @Override
    public void deleteByID(Today today) {

    }

    @Override
    public Today selectByID(int i) {
        return null;
    }

    @Override
    public List<Today> selectAll() {
        return null;
    }
}
