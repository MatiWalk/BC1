package dao;

import model.Wind;

import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class WindDAO implements ClimateDAO<Wind> {


    @Override
    public int insert(Wind wind) {
        return 0;
    }

    @Override
    public void update(Wind wind, int id) {

    }

    @Override
    public void deleteByID(int id) {

    }

    @Override
    public Wind selectByID(int id) {
        return null;
    }

    @Override
    public List<Wind> selectAll() {
        return null;
    }
}
