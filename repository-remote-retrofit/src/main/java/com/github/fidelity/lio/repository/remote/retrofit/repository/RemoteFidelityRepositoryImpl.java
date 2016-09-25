package com.github.fidelity.lio.repository.remote.retrofit.repository;

import com.github.fidelity.lio.lojista.domain.Checkout;
import com.github.fidelity.lio.lojista.domain.RemoteFidelityRepository;
import com.github.fidelity.lio.repository.remote.retrofit.FidelityApi;

import javax.inject.Inject;

import rx.Observable;


public class RemoteFidelityRepositoryImpl implements RemoteFidelityRepository {
    private FidelityApi fidelityApi;

    @Inject
    RemoteFidelityRepositoryImpl(FidelityApi fidelityApi) {
        this.fidelityApi = fidelityApi;
    }

    @Override
    public Observable<Void> checkoutOrder(String merchantId, String orderId, String pointsQuantity,
                                          String amount, String fidelityChannel,
                                          String phoneNumber) {
        return fidelityApi.checkout(new Checkout(merchantId, orderId, pointsQuantity, amount,
                fidelityChannel, phoneNumber));
    }
}
