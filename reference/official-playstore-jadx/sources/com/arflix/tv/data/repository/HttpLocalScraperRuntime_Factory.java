package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class HttpLocalScraperRuntime_Factory implements Factory<HttpLocalScraperRuntime> {
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<TmdbApi> tmdbApiProvider;

    private HttpLocalScraperRuntime_Factory(Provider<Context> provider, Provider<gb.h0> provider2, Provider<TmdbApi> provider3) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.tmdbApiProvider = provider3;
    }

    public static HttpLocalScraperRuntime_Factory create(Provider<Context> provider, Provider<gb.h0> provider2, Provider<TmdbApi> provider3) {
        return new HttpLocalScraperRuntime_Factory(provider, provider2, provider3);
    }

    public static HttpLocalScraperRuntime newInstance(Context context, gb.h0 h0Var, TmdbApi tmdbApi) {
        return new HttpLocalScraperRuntime(context, h0Var, tmdbApi);
    }

    @Override // javax.inject.Provider
    public HttpLocalScraperRuntime get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get(), this.tmdbApiProvider.get());
    }
}
