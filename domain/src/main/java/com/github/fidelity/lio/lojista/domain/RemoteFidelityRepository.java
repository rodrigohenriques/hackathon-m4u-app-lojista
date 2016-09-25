package com.github.fidelity.lio.lojista.domain;

import rx.Observable;

public interface RemoteFidelityRepository {

    Observable<Void> checkoutOrder(String merchant, String orderId, Long pointsQuantity,
                                   Long amount, String fidelityChannel, String phone);
}