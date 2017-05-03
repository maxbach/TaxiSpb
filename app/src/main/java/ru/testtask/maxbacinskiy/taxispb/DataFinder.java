package ru.testtask.maxbacinskiy.taxispb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class DataFinder {

    private List<TaxiOrder> taxiOrders;

    public DataFinder() {
        taxiOrders = new ArrayList<>();
        URL url = NetworkUtils.buildInfoUrl();
        String jsonInfo;
        try {
            jsonInfo = NetworkUtils.getResponceFromUrl(url);
            buildListOfOrdersFromJson(jsonInfo);
        } catch (IOException e) {
            e.printStackTrace();
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

    private void buildListOfOrdersFromJson(String jsonString) {
        JSONArray json = null;
        try {
            json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {

                JSONObject jsonObject = json.getJSONObject(i);
                TaxiOrder order = new TaxiOrder();

                JSONObject addressA = jsonObject.getJSONObject("startAddress");
                String pointA = addressA.getString("city") + " " + addressA.getString("address");
                order.setPointA(pointA);

                JSONObject addressB = jsonObject.getJSONObject("endAddress");
                String pointB = addressB.getString("city") + " " + addressB.getString("address");
                order.setPointB(pointB);

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


}
