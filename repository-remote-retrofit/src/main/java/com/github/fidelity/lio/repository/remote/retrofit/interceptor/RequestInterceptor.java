package com.github.fidelity.lio.repository.remote.retrofit.interceptor;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {
    private String clientId;
    private String accessToken;
    private String merchantId;

    @Inject
    public RequestInterceptor(String clientId, String accessToken, String merchantId) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.merchantId = merchantId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request newRequest = request.newBuilder()
                .addHeader("client-id", clientId)
                .addHeader("access-token", accessToken)
                .addHeader("merchant-id", merchantId)
                .build();

        return chain.proceed(newRequest);
    }
}
