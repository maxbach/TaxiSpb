package ru.testtask.maxbacinskiy.taxispb;

import java.io.Serializable;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class TaxiOrder implements Serializable {
    private String pointA;
    private String pointB;
    private String date;
    private String cost;
    private String time;
    private String carDriver;
    private String carBrand;
    private String carNumber;
    private String imageWay;

    public TaxiOrder() {
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarDriver() {
        return carDriver;
    }

    public void setCarDriver(String carDriver) {
        this.carDriver = carDriver;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getImageWay() {
        return imageWay;
    }

    public void setImageWay(String imageWay) {
        this.imageWay = imageWay;
    }
}
