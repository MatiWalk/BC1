package dao;

import model.Astronomy;

import java.util.List;

/**
 * Created by Mati on 16/05/2017.
 */
public class AstronomyDao implements weatherDAO<Astronomy> {


    @Override
    public int insert(Astronomy astronomy) {
        return 0;
    }

    @Override
    public void update(Astronomy astronomy) {

    }

    @Override
    public void delete(Astronomy astronomy) {

    }

    @Override
    public Astronomy selectByID(int i) {
        return null;
    }

    @Override
    public List<Astronomy> selectAll() {
        return null;
    }
}
