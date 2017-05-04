package ru.testtask.maxbacinskiy.taxispb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by maxbacinskiy on 01.05.17.
 */

public class TaxiAdapter extends RecyclerView.Adapter<TaxiAdapter.TaxiOrderViewHolder> {

    private DataFinder df;

    private final TaxiAdapterOnClickListener mClickHandler;

    public interface TaxiAdapterOnClickListener {
        void onClick(TaxiOrder order);
    }

    public TaxiAdapter(TaxiAdapterOnClickListener listener) {
        df = new DataFinder();
        mClickHandler = listener;
    }

    @Override
    public TaxiOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdListItem, parent, false);

        return new TaxiOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaxiOrderViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return df.getOrdersCount();
    }

    public void refresh() {
        df.refresh();
    }

    public class TaxiOrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView m_route;
        TextView m_date;
        TextView m_cost;


        public TaxiOrderViewHolder(View itemView) {
            super(itemView);

            m_route = (TextView) itemView.findViewById(R.id.tv_address);
            m_date = (TextView) itemView.findViewById(R.id.tv_date);
            m_cost = (TextView) itemView.findViewById(R.id.tv_money);

            itemView.setOnClickListener(this);

        }

        void bind(int listItemIndex) {
            TaxiOrder order = df.getOrder(listItemIndex);
            String city1 = order.getPointA().getCity();
            String city2 = order.getPointB().getCity();
            String line;
            if (city1.equals(city2)) {
                line = order.getPointA().getAddress() + " - " + order.getPointB().getAddress();

            } else {
                line = order.getPointA().getAddress() + " (" + city1 + ") " +
                        " - " + order.getPointB().getAddress() + " (" + city2 + ")";
            }

            m_route.setText(line);
            m_date.setText(order.getDate());
            m_cost.setText(order.getCost());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TaxiOrder order = df.getOrder(position);
            mClickHandler.onClick(order);
        }
    }
}
