package com.github.fidelity.lio.merchant;

import android.app.Application;

import com.digits.sdk.android.Digits;
import com.github.fidelity.lio.merchant.di.component.ApplicationComponent;
import com.github.fidelity.lio.merchant.di.component.DaggerApplicationComponent;
import com.github.fidelity.lio.merchant.di.module.ApplicationModule;
import com.github.fidelity.lio.merchant.di.module.RemoteRetrofitModule;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import io.fabric.sdk.android.Fabric;

public class MerchantApplication extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "HMzFMInx3Ma8yltWGTK6PubSS";
    private static final String TWITTER_SECRET = "HSvvp9jyp2uYuI8E5qW4E3iXTi55TmpTf2n7G66E79tDhsqL49";

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());

        this.applicationComponent = DaggerApplicationComponent.builder()
                                    .applicationModule(new ApplicationModule(this))
                                    .remoteRetrofitModule(new RemoteRetrofitModule())
                                    .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
