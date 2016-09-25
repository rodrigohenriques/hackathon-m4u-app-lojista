package com.github.fidelity.lio.lojista.domain;

import java.util.List;

import rx.Observable;

public interface RemoteOrderRepository {
    Observable<List<Order>> retrieveAllOrders();
}
