package com.github.fidelity.lio.merchant.di.module;

import com.github.fidelity.lio.lojista.domain.RemoteOrderRepository;
import com.github.fidelity.lio.merchant.di.annotations.AccessToken;
import com.github.fidelity.lio.merchant.di.annotations.ClientId;
import com.github.fidelity.lio.merchant.di.annotations.MerchantId;
import com.github.fidelity.lio.repository.remote.di.annotations.RemoteUrl;
import com.github.fidelity.lio.repository.remote.retrofit.OrderManagementApi;
import com.github.fidelity.lio.repository.remote.retrofit.interceptor.RequestInterceptor;
import com.github.fidelity.lio.repository.remote.retrofit.repository.RemoteOrderRepositoryImpl;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RemoteRetrofitModule {

    @Provides
    @RemoteUrl
    String provideRemoteUrl() {
        return "https://api.cielo.com.br/sandbox-lio/order-management/v1/";
    }

    @Provides
    @ClientId
    String provideClientId() {
        return "VeRLsahk8qcw";
    }

    @Provides
    @AccessToken
    String provideAccessToken() {
        return "YTFQVHsrxcIO";
    }

    @Provides
    @MerchantId
    String provideMerchantId() {
        return "5d4261c1-b5a0-423b-8029-d98073b8b962";
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    RequestInterceptor provideRequestInterceptor(@ClientId String clientId, @AccessToken
            String accessToken, @MerchantId String merchantId) {
        return new RequestInterceptor(clientId, accessToken, merchantId);
    }

    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, RequestInterceptor
            requestInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(requestInterceptor)
                .build();
    }

    @Provides
    Converter.Factory provideConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        //        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    @Provides
    Retrofit provideRetrofit(@RemoteUrl String remoteUrl, OkHttpClient okHttpClient,
                             Converter.Factory converterFactory) {
        return new Retrofit.Builder().baseUrl(remoteUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    OrderManagementApi provideOrderManagementApi(Retrofit retrofit) {
        return retrofit.create(OrderManagementApi.class);
    }

    @Provides
    RemoteOrderRepository provideRemoteCharactersRepository(RemoteOrderRepositoryImpl
                                                                    remoteOrderRepository) {
        return remoteOrderRepository;
    }
}