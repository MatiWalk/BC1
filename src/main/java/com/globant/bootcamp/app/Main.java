package com.globant.bootcamp.app;

import com.globant.bootcamp.builder.*;
import com.globant.bootcamp.connection.DBConnector;
import com.globant.bootcamp.model.WeatherCode;
import com.globant.bootcamp.model.*;
import com.globant.bootcamp.model.unit.Temperature;
import com.globant.bootcamp.persistence.ClimateCRUD;
import com.globant.bootcamp.persistence.ResultCRUD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        App a = (App) context.getBean("app");
        a.run();
    }

}
