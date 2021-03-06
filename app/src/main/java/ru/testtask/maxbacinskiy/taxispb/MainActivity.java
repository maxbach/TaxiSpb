package ru.testtask.maxbacinskiy.taxispb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements TaxiAdapter.TaxiAdapterOnClickListener {

    private TaxiAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipe;
    public static final String TAXI_ORDER_TAG = "TaxiOrderTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_screen);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        new GetInfoTask().execute(this);

        mSwipe = (SwipeRefreshLayout) findViewById(R.id.srl_layout);

        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RefreshTask().execute();
            }
        });

    }

    @Override
    public void onClick(TaxiOrder order) {
        Intent intentToStart = new Intent(this, OrderDetailActivity.class);
        intentToStart.putExtra(TAXI_ORDER_TAG, order);
        startActivity(intentToStart);
    }


    public class RefreshTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            mAdapter.refresh();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mAdapter.notifyDataSetChanged();
            mSwipe.setRefreshing(false);

        }
    }

    public class GetInfoTask extends AsyncTask<TaxiAdapter.TaxiAdapterOnClickListener, Void, TaxiAdapter> {


        @Override
        protected TaxiAdapter doInBackground(TaxiAdapter.TaxiAdapterOnClickListener... taxiAdapterOnClickListeners) {
            TaxiAdapter.TaxiAdapterOnClickListener listener = taxiAdapterOnClickListeners[0];
            return new TaxiAdapter(listener);
        }

        @Override
        protected void onPostExecute(TaxiAdapter taxiAdapter) {
            mAdapter = taxiAdapter;
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
