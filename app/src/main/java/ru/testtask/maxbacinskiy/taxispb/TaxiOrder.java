package ru.testtask.maxbacinskiy.taxispb;

import java.io.Serializable;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class TaxiOrder implements Serializable {
    String pointA;
    String pointB;
    String date;
    String cost;
    String time;
    String carDriver;
    String carBrand;
    String carNumber;

    public TaxiOrder(String pointA, String pointB, String date, String cost, String time, String carDriver, String carBrand, String carNumber) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.date = date;
        this.cost = cost;
        this.time = time;
        this.carDriver = carDriver;
        this.carBrand = carBrand;
        this.carNumber = carNumber;
    }
}
