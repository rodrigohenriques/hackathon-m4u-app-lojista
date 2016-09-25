package com.github.fidelity.lio.merchant.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.lojista.domain.RemoteOrderRepository;
import com.github.fidelity.lio.merchant.MerchantApplication;
import com.github.fidelity.lio.merchant.R;
import com.github.fidelity.lio.merchant.ui.adapter.OrdersListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject RemoteOrderRepository remoteOrderRepository;

    @Bind(R.id.orders_list) RecyclerView ordersRecyclerView;
    @Bind(R.id.loading_view) ProgressBar loadingView;
    @Bind(R.id.no_items_view) TextView noItemsView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @BindColor(android.R.color.white)
    int white;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MerchantApplication) getApplication()).getApplicationComponent().inject(this);
        toolbar.setTitle("Lista de pedidos");
        toolbar.setBackgroundColor(white);
    }

    @Override
    protected void onStart() {
        super.onStart();

        remoteOrderRepository.retrieveAllOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> setLoadingMode(true))
                .subscribe(this::buildOrdersList,
                        t -> Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show(),
                        () -> loadingView.setVisibility(View.GONE));
    }

    private void setLoadingMode(boolean loadingMode) {
        loadingView.setVisibility(loadingMode ? View.VISIBLE : View.GONE);
        ordersRecyclerView.setVisibility(loadingMode ? View.GONE : View.VISIBLE);
    }

    private void buildOrdersList(List<Order> orders) {
        if (orders != null && orders.size() > 0) {
            noItemsView.setVisibility(View.GONE);
            ordersRecyclerView.setVisibility(View.VISIBLE);

            OrdersListAdapter ordersListAdapter = new OrdersListAdapter(this, orders);
            ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            ordersRecyclerView.setAdapter(ordersListAdapter);
        } else {
            noItemsView.setVisibility(View.VISIBLE);
            ordersRecyclerView.setVisibility(View.GONE);
        }
    }

}