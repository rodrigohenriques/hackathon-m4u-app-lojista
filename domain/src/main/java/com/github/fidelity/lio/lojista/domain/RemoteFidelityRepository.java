package com.github.fidelity.lio.lojista.domain;

import rx.Observable;

public interface RemoteFidelityRepository {

    Observable<Void> checkoutOrder(String merchantId, String orderId, String pointsQuantity,
                                   String amount, String fidelityChannel,
                                   String phoneNumber);
}