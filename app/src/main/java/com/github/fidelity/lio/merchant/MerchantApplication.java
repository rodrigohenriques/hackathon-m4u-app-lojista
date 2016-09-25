package com.github.fidelity.lio.merchant;

import android.app.Application;

import com.github.fidelity.lio.merchant.di.component.ApplicationComponent;
import com.github.fidelity.lio.merchant.di.component.DaggerApplicationComponent;
import com.github.fidelity.lio.merchant.di.module.ApplicationModule;
import com.github.fidelity.lio.merchant.di.module.RemoteRetrofitModule;

public class MerchantApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = DaggerApplicationComponent.builder()
                                    .applicationModule(new ApplicationModule(this))
                                    .remoteRetrofitModule(new RemoteRetrofitModule())
                                    .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
