package ru.testtask.maxbacinskiy.taxispb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class DataFinder {

    private List<TaxiOrder> taxiOrders;

    public DataFinder() {
        taxiOrders = new ArrayList<>();
        loadInfo();

    }

    public int getOrdersCount() {
        return taxiOrders.size();
    }

    public void refresh() {
        taxiOrders.clear();
        loadInfo();
    }

    public void loadInfo() {
        URL url = NetworkUtils.buildInfoUrl();
        String jsonInfo;
        try {
            jsonInfo = NetworkUtils.getResponceFromUrl(url);
            buildListOfOrdersFromJson(jsonInfo);
            sortItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TaxiOrder> getOrders() {
        return taxiOrders;
    }

    public TaxiOrder getOrder(int index) {
        return taxiOrders.get(index);
    }

    private void buildListOfOrdersFromJson(String jsonString) {
        JSONArray json = null;
        try {
            json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {

                JSONObject jsonObject = json.getJSONObject(i);
                TaxiOrder order = new TaxiOrder();

                JSONObject addressA = jsonObject.getJSONObject("startAddress");
                String city = addressA.getString("city");
                String address = addressA.getString("address");
                order.setPointA(new TaxiOrder.Address(city, address));

                JSONObject addressB = jsonObject.getJSONObject("endAddress");
                city = addressB.getString("city");
                address = addressB.getString("address");
                order.setPointB(new TaxiOrder.Address(city, address));

                JSONObject costJson = jsonObject.getJSONObject("price");
                Double amount = Double.parseDouble(costJson.getString("amount")) / 100;
                String cost = amount + " " + costJson.getString("currency");
                order.setCost(cost);

                SimpleDateFormat iso8061 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

                Date date = iso8061.parse(jsonObject.getString("orderTime"));
                order.setDate(dateFormat.format(date));
                order.setTime(timeFormat.format(date));

                JSONObject carInfo = jsonObject.getJSONObject("vehicle");

                String carNumber = carInfo.getString("regNumber");
                order.setCarNumber(carNumber);

                String brand = carInfo.getString("modelName");
                order.setCarBrand(brand);

                String imageWay = carInfo.getString("photo");
                order.setImageWay(imageWay);

                String driver = carInfo.getString("driverName");
                order.setCarDriver(driver);

                taxiOrders.add(order);

            }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }


    }

    private void sortItems() {
        Collections.sort(taxiOrders, new Comparator<TaxiOrder>() {
            @Override
            public int compare(TaxiOrder taxiOrder, TaxiOrder t1) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date1 = dateFormat.parse(taxiOrder.getDate());
                    Date date2 = dateFormat.parse(t1.getDate());
                    int compare = date2.compareTo(date1);
                    if (compare == 0) {
                        date1 = timeFormat.parse(taxiOrder.getTime());
                        date2 = timeFormat.parse(t1.getTime());
                        compare = date2.compareTo(date1);
                    }
                    return compare;
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return 0;
                }
            }
        });
    }


}
