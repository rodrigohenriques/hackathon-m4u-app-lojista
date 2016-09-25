package com.github.fidelity.lio.repository.remote.retrofit.repository;

import com.github.fidelity.lio.lojista.domain.Checkout;
import com.github.fidelity.lio.lojista.domain.RemoteFidelityRepository;
import com.github.fidelity.lio.lojista.domain.annotations.AccessToken;
import com.github.fidelity.lio.lojista.domain.annotations.ClientId;
import com.github.fidelity.lio.lojista.domain.annotations.MerchantId;
import com.github.fidelity.lio.repository.remote.retrofit.FidelityApi;

import javax.inject.Inject;

import rx.Observable;


public class RemoteFidelityRepositoryImpl implements RemoteFidelityRepository {
    private FidelityApi fidelityApi;
    private String merchantId;
    private String accessToken;
    private String clientId;

    @Inject
    RemoteFidelityRepositoryImpl(FidelityApi fidelityApi, @MerchantId String merchantId,
                                 @AccessToken String accessToken, @ClientId String clientId) {
        this.fidelityApi = fidelityApi;
        this.merchantId = merchantId;
        this.accessToken = accessToken;
        this.clientId = clientId;
    }

    @Override
    public Observable<Void> checkoutOrder(String merchant, String orderId, Long pointsQuantity,
                                          Long amount, String fidelityChannel,
                                          String phoneNumber) {
        return fidelityApi.checkout(new Checkout(merchantId, accessToken, clientId, merchant,
                orderId, pointsQuantity, amount, fidelityChannel, phoneNumber));
    }
}
