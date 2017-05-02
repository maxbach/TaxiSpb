package ru.testtask.maxbacinskiy.taxispb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView mAddressFrom;
    private TextView mAddressTo;
    private TextView mCarNumber;
    private TextView mCarDriver;
    private TextView mCarBrand;
    private TextView mDate;
    private TextView mTime;
    private TextView mCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mAddressFrom = (TextView) findViewById(R.id.tv_info_address_from);
        mAddressTo = (TextView) findViewById(R.id.tv_info_address_to);
        mCarNumber = (TextView) findViewById(R.id.tv_info_car_number);
        mCarDriver = (TextView) findViewById(R.id.tv_info_car_driver);
        mCarBrand = (TextView) findViewById(R.id.tv_info_car_brand);
        mDate = (TextView) findViewById(R.id.tv_info_date);
        mTime = (TextView) findViewById(R.id.tv_info_time);
        mCost = (TextView) findViewById(R.id.tv_info_cost);


        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(MainActivity.TAXI_ORDER_TAG)) {
                TaxiOrder order = (TaxiOrder) intent.getSerializableExtra(MainActivity.TAXI_ORDER_TAG);

                mAddressFrom.setText("From: " + order.pointA);
                mAddressTo.setText("To: " + order.pointB);
                mCarNumber.setText(order.carNumber);
                mCarDriver.setText("Водила: " + order.carDriver);
                mCarBrand.setText("Тачила: " + order.carBrand);
                mDate.setText(order.date);
                mTime.setText(order.time);
                mCost.setText(order.cost);

            }
        }

    }
}
