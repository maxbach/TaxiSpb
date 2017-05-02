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
                    "Московский вокзал", "16.02.2014", "155 RUB", "18:56", "Михаил", "BMW i5",
                    " В 888 ВВ 25 RUS"));
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


}
