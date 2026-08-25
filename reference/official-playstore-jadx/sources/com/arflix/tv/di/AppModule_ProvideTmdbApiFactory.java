package com.arflix.tv.di;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideTmdbApiFactory implements Factory<TmdbApi> {
    private final Provider<Context> contextProvider;
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideTmdbApiFactory(Provider<h0> provider, Provider<Context> provider2) {
        this.okHttpClientProvider = provider;
        this.contextProvider = provider2;
    }

    public static AppModule_ProvideTmdbApiFactory create(Provider<h0> provider, Provider<Context> provider2) {
        return new AppModule_ProvideTmdbApiFactory(provider, provider2);
    }

    public static TmdbApi provideTmdbApi(h0 h0Var, Context context) {
        return (TmdbApi) Preconditions.checkNotNullFromProvides(AppModule.provideTmdbApi(h0Var, context));
    }

    @Override // javax.inject.Provider
    public TmdbApi get() {
        return provideTmdbApi(this.okHttpClientProvider.get(), this.contextProvider.get());
    }
}
