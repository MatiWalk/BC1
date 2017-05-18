package dao;

import model.Result;

import java.util.List;

/**
 * Created by Sistemas on 16/5/2017.
 */
public class ResultDAO implements ClimateDAO<Result> {
    @Override
    public int insert(Result result) {
        return 0;
    }

    @Override
    public void update(Result result, int id) {

    }

    @Override
    public void deleteByID(int id) {

    }


    @Override
    public Result selectByID(int i) {
        return null;
    }

    @Override
    public List<Result> selectAll() {
        return null;
    }
}
