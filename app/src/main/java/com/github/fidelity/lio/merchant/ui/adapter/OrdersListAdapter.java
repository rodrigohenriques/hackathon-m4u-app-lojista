package com.github.fidelity.lio.merchant.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.merchant.R;
import com.github.fidelity.lio.merchant.entities.Extra;
import com.github.fidelity.lio.merchant.ui.DetailActivity;

import java.util.List;

import rx.functions.Func1;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.OrderViewHolder> {
    private Activity activity;
    private List<Order> orders;
    private Func1<Long, String> amountFormatter = String::valueOf;

    public OrdersListAdapter(Activity activity, List<Order> orders) {
        this.activity = activity;
        this.orders = orders;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View orderLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,
                parent, false);

        return new OrderViewHolder(orderLayout);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.orderSummary.setText(order.getReference());
        holder.orderAmount.setText(amountFormatter.call(order.getPrice().longValue()));

        holder.view.setOnClickListener(view -> {
            Intent openDetail = new Intent(activity, DetailActivity.class);
            openDetail.putExtra(Extra.ORDER, order);
            activity.startActivity(openDetail);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setAmountFormatter(Func1<Long, String> amountFormatter) {
        this.amountFormatter = amountFormatter;
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView orderSummary;
        TextView orderAmount;

        OrderViewHolder(View itemView) {
            super(itemView);
            this.orderSummary = (TextView) itemView.findViewById(R.id.order_summary);
            this.orderAmount = (TextView) itemView.findViewById(R.id.order_amount);
            this.view = itemView;
        }
    }
}
