package com.github.fidelity.lio.repository.remote.retrofit;

import com.github.fidelity.lio.lojista.domain.Order;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface OrderManagementApi {

    @GET("orders")
    Observable<List<Order>> getOrders();
}
