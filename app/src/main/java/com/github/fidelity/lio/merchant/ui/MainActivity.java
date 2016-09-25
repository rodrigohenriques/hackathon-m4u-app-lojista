package com.github.fidelity.lio.merchant.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.lojista.domain.RemoteOrderRepository;
import com.github.fidelity.lio.merchant.MerchantApplication;
import com.github.fidelity.lio.merchant.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    @Inject RemoteOrderRepository remoteOrderRepository;

    @Bind(R.id.orders_list) RecyclerView ordersList;
    @Bind(R.id.loading_view) ProgressBar loadingView;
    @Bind(R.id.orders) TextView ordersText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MerchantApplication) getApplication()).getApplicationComponent().inject(this);
    }

    @OnClick(R.id.get_orders)
    void onGetOrdersClick() {
        remoteOrderRepository.retrieveAllOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> loadingView.setVisibility(View.VISIBLE))
                .subscribe(orders -> ordersText.setText(buildOrdersString(orders)),
                        t -> Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show(),
                        () -> loadingView.setVisibility(View.GONE));
    }

    private String buildOrdersString(List<Order> orders) {
        StringBuilder ordersStringBuilder = new StringBuilder("");

        for (Order order : orders) {
            ordersStringBuilder.append(order.getId());
            ordersStringBuilder.append("\n");
        }

        return ordersStringBuilder.toString();
    }
}