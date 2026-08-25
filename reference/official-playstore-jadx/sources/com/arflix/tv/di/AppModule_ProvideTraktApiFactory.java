package com.arflix.tv.di;

import com.arflix.tv.data.api.TraktApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideTraktApiFactory implements Factory<TraktApi> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideTraktApiFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideTraktApiFactory create(Provider<h0> provider) {
        return new AppModule_ProvideTraktApiFactory(provider);
    }

    public static TraktApi provideTraktApi(h0 h0Var) {
        return (TraktApi) Preconditions.checkNotNullFromProvides(AppModule.provideTraktApi(h0Var));
    }

    @Override // javax.inject.Provider
    public TraktApi get() {
        return provideTraktApi(this.okHttpClientProvider.get());
    }
}
