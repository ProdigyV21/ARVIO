package com.arflix.tv.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideAniSkipRetrofitFactory implements Factory<u0> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideAniSkipRetrofitFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideAniSkipRetrofitFactory create(Provider<h0> provider) {
        return new AppModule_ProvideAniSkipRetrofitFactory(provider);
    }

    public static u0 provideAniSkipRetrofit(h0 h0Var) {
        return (u0) Preconditions.checkNotNullFromProvides(AppModule.provideAniSkipRetrofit(h0Var));
    }

    @Override // javax.inject.Provider
    public u0 get() {
        return provideAniSkipRetrofit(this.okHttpClientProvider.get());
    }
}
