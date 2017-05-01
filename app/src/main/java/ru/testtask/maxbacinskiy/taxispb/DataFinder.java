package ru.testtask.maxbacinskiy.taxispb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class DataFinder {

    private List<TaxiOrder> taxiOrders;

    public DataFinder() {
        taxiOrders = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            taxiOrders.add(new TaxiOrder("Новоизмайловский проспект 16 к.2",
                    "Московский вокзал", "16.02.2014", "155 RUB"));
        }
    }

    public int getOrdersCount() {
        return taxiOrders.size();
    }

    public List<TaxiOrder> getOrders() {
        return taxiOrders;
    }

    public TaxiOrder getOrder(int index) {
        return taxiOrders.get(index);
    }

    public class TaxiOrder {
        String pointA;
        String pointB;
        String date;
        String cost;

        public TaxiOrder(String pointA, String pointB, String date, String cost) {
            this.pointA = pointA;
            this.pointB = pointB;
            this.date = date;
            this.cost = cost;
        }
    }

}
