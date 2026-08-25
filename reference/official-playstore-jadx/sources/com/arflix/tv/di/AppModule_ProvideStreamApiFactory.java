package com.arflix.tv.di;

import com.arflix.tv.data.api.StreamApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideStreamApiFactory implements Factory<StreamApi> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideStreamApiFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideStreamApiFactory create(Provider<h0> provider) {
        return new AppModule_ProvideStreamApiFactory(provider);
    }

    public static StreamApi provideStreamApi(h0 h0Var) {
        return (StreamApi) Preconditions.checkNotNullFromProvides(AppModule.provideStreamApi(h0Var));
    }

    @Override // javax.inject.Provider
    public StreamApi get() {
        return provideStreamApi(this.okHttpClientProvider.get());
    }
}
