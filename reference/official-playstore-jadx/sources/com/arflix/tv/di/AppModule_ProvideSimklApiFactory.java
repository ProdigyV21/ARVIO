package com.arflix.tv.di;

import com.arflix.tv.data.api.SimklApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideSimklApiFactory implements Factory<SimklApi> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideSimklApiFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideSimklApiFactory create(Provider<h0> provider) {
        return new AppModule_ProvideSimklApiFactory(provider);
    }

    public static SimklApi provideSimklApi(h0 h0Var) {
        return (SimklApi) Preconditions.checkNotNullFromProvides(AppModule.provideSimklApi(h0Var));
    }

    @Override // javax.inject.Provider
    public SimklApi get() {
        return provideSimklApi(this.okHttpClientProvider.get());
    }
}
