package com.github.fidelity.lio.merchant.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.merchant.R;

import java.util.List;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.OrderViewHolder> {
    private List<Order> orders;

    public OrdersListAdapter(List<Order> orders) {
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
        holder.orderAmount.setText(order.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderSummary;
        TextView orderAmount;

        OrderViewHolder(View itemView) {
            super(itemView);
            orderSummary = (TextView) itemView.findViewById(R.id.order_summary);
            orderAmount = (TextView) itemView.findViewById(R.id.order_amount);
        }
    }
}
