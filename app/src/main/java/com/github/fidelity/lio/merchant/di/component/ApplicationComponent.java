package com.github.fidelity.lio.merchant.di.component;

import com.github.fidelity.lio.merchant.di.module.ApplicationModule;
import com.github.fidelity.lio.merchant.di.module.RemoteRetrofitModule;
import com.github.fidelity.lio.merchant.ui.DetailActivity;
import com.github.fidelity.lio.merchant.ui.MainActivity;

import dagger.Component;

@Component(modules = {ApplicationModule.class, RemoteRetrofitModule.class})
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
