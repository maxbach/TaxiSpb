package ru.testtask.maxbacinskiy.taxispb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class OrderDetailActivity extends AppCompatActivity {

    private static CacheBItmapHelper cacheHelper;
    private TextView mAddressFrom;
    private TextView mAddressTo;
    private TextView mCarNumber;
    private TextView mCarDriver;
    private TextView mCarBrand;
    private TextView mDate;
    private TextView mTime;
    private TextView mCost;
    private ImageView mCarImage;

    static {
        cacheHelper = new CacheBItmapHelper();
    }

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
        mCarImage = (ImageView) findViewById(R.id.iv_info_car_image);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(MainActivity.TAXI_ORDER_TAG)) {
                TaxiOrder order = (TaxiOrder) intent.getSerializableExtra(MainActivity.TAXI_ORDER_TAG);

                mAddressFrom.setText("From: " + order.getPointA());
                mAddressTo.setText("To: " + order.getPointB());
                mCarNumber.setText(order.getCarNumber());
                mCarDriver.setText("Водила: " + order.getCarDriver());
                mCarBrand.setText("Тачила: " + order.getCarBrand());
                mDate.setText(order.getDate());
                mTime.setText(order.getTime());
                mCost.setText(order.getCost());
                new ImageLoadTask().execute(order.getImageWay());

            }
        }

    }

    public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String imageWay = strings[0];
            Context context = OrderDetailActivity.this.getApplicationContext();

            Bitmap bmp = cacheHelper.getBitmap(imageWay);

            if (bmp == null) {

                bmp = ImageMemoryUtils.loadImageFromInternal(imageWay, context);

                if (bmp == null) {
                    URL url = NetworkUtils.buildImageUrl(imageWay);

                    try {
                        bmp = NetworkUtils.getImageFromUrl(url);
                        ImageMemoryUtils.saveImageToInternal(imageWay, bmp, context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                cacheHelper.addBitmap(imageWay, bmp);
            }

            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mCarImage.setImageBitmap(bitmap);
        }
    }
}
