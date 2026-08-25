package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TraktApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CatalogDiscoveryRepository_Factory implements Factory<CatalogDiscoveryRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<TraktApi> traktApiProvider;

    private CatalogDiscoveryRepository_Factory(Provider<Context> provider, Provider<TraktApi> provider2, Provider<gb.h0> provider3) {
        this.contextProvider = provider;
        this.traktApiProvider = provider2;
        this.okHttpClientProvider = provider3;
    }

    public static CatalogDiscoveryRepository_Factory create(Provider<Context> provider, Provider<TraktApi> provider2, Provider<gb.h0> provider3) {
        return new CatalogDiscoveryRepository_Factory(provider, provider2, provider3);
    }

    public static CatalogDiscoveryRepository newInstance(Context context, TraktApi traktApi, gb.h0 h0Var) {
        return new CatalogDiscoveryRepository(context, traktApi, h0Var);
    }

    @Override // javax.inject.Provider
    public CatalogDiscoveryRepository get() {
        return newInstance(this.contextProvider.get(), this.traktApiProvider.get(), this.okHttpClientProvider.get());
    }
}
