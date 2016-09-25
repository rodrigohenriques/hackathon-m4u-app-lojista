package com.github.fidelity.lio.repository.remote.retrofit;

import com.github.fidelity.lio.lojista.domain.Checkout;

import retrofit2.http.GET;
import rx.Observable;

public interface FidelityApi {

    @GET("checkout")
    Observable<Void> checkout(Checkout checkout);
}
