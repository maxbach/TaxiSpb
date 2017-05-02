package ru.testtask.maxbacinskiy.taxispb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements TaxiAdapter.TaxiAdapterOnClickListener {

    private TaxiAdapter mAdapter;
    private RecyclerView mRecyclerView;
    public static final String TAXI_ORDER_TAG = "TaxiOrderTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_screen);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new TaxiAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(TaxiOrder order) {
        Intent intentToStart = new Intent(this, OrderDetailActivity.class);
        intentToStart.putExtra(TAXI_ORDER_TAG, order);
        startActivity(intentToStart);
    }
}
