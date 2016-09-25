package com.github.fidelity.lio.repository.remote.retrofit.repository;

import com.github.fidelity.lio.lojista.domain.Order;
import com.github.fidelity.lio.lojista.domain.RemoteOrderRepository;
import com.github.fidelity.lio.repository.remote.retrofit.OrderManagementApi;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RemoteOrderRepositoryImpl implements RemoteOrderRepository {
    private OrderManagementApi orderManagementApi;

    @Inject
    RemoteOrderRepositoryImpl(OrderManagementApi orderManagementApi) {
        this.orderManagementApi = orderManagementApi;
    }

    @Override
    public Observable<List<Order>> retrieveAllOrders() {
        return orderManagementApi.getOrders();
    }
}
