package com.github.fidelity.lio.repository.remote.retrofit;

import com.github.fidelity.lio.lojista.domain.Checkout;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface FidelityApi {

    @POST("checkout")
    Observable<Void> checkout(@Body Checkout checkout);
}
