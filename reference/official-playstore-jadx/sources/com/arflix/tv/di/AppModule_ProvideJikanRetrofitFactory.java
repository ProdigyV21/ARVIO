package com.arflix.tv.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideJikanRetrofitFactory implements Factory<u0> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideJikanRetrofitFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideJikanRetrofitFactory create(Provider<h0> provider) {
        return new AppModule_ProvideJikanRetrofitFactory(provider);
    }

    public static u0 provideJikanRetrofit(h0 h0Var) {
        return (u0) Preconditions.checkNotNullFromProvides(AppModule.provideJikanRetrofit(h0Var));
    }

    @Override // javax.inject.Provider
    public u0 get() {
        return provideJikanRetrofit(this.okHttpClientProvider.get());
    }
}
