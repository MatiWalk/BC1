package model;

import controller.DBConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mati on 17/05/2017.
 */

@Component
public class test {

    @Autowired
    public DBConnector dbConnector;


    public test() {
    }


}
