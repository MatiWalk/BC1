package com.globant.bootcamp.model;

import com.globant.bootcamp.connection.DBConnector;
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
