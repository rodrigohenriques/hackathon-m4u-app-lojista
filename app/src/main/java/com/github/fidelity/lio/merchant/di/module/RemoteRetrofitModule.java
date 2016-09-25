package com.github.fidelity.lio.merchant.di.module;

import com.github.fidelity.lio.lojista.domain.RemoteFidelityRepository;
import com.github.fidelity.lio.lojista.domain.RemoteOrderRepository;
import com.github.fidelity.lio.lojista.domain.annotations.AccessToken;
import com.github.fidelity.lio.lojista.domain.annotations.ClientId;
import com.github.fidelity.lio.lojista.domain.annotations.MerchantId;
import com.github.fidelity.lio.repository.remote.di.annotations.Fidelity;
import com.github.fidelity.lio.repository.remote.di.annotations.Lio;
import com.github.fidelity.lio.repository.remote.retrofit.FidelityApi;
import com.github.fidelity.lio.repository.remote.retrofit.OrderManagementApi;
import com.github.fidelity.lio.repository.remote.retrofit.interceptor.RequestInterceptor;
import com.github.fidelity.lio.repository.remote.retrofit.repository.RemoteFidelityRepositoryImpl;
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
    @Lio
    String provideLioRemoteUrl() {
        return "https://api.cielo.com.br/sandbox-lio/order-management/v1/";
    }

    @Provides
    @Fidelity
    String provideFidelityRemoteUrl() {
        return "https://lio-fidelidade.herokuapp.com/api/";
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
    OrderManagementApi provideOrderManagementApi(@Lio String remoteUrl,
                                                 OkHttpClient okHttpClient,
                                                 Converter.Factory converterFactory) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(remoteUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(OrderManagementApi.class);
    }

    @Provides
    FidelityApi provideFidelityApi(@Fidelity String remoteUrl,
                                   OkHttpClient okHttpClient,
                                   Converter.Factory converterFactory) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(remoteUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(FidelityApi.class);
    }

    @Provides
    RemoteOrderRepository provideRemoteOrdersRepository(RemoteOrderRepositoryImpl
                                                                    remoteOrderRepository) {
        return remoteOrderRepository;
    }

    @Provides
    RemoteFidelityRepository provideRemoteFidelityRepository(RemoteFidelityRepositoryImpl
                                                                     remoteFidelityRepository) {
        return remoteFidelityRepository;
    }
}