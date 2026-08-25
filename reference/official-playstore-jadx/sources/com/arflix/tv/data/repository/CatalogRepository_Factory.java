package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TraktApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CatalogRepository_Factory implements Factory<CatalogRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<TraktApi> traktApiProvider;

    private CatalogRepository_Factory(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktApi> provider3, Provider<gb.h0> provider4, Provider<CloudSyncInvalidationBus> provider5) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
        this.traktApiProvider = provider3;
        this.okHttpClientProvider = provider4;
        this.invalidationBusProvider = provider5;
    }

    public static CatalogRepository_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktApi> provider3, Provider<gb.h0> provider4, Provider<CloudSyncInvalidationBus> provider5) {
        return new CatalogRepository_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static CatalogRepository newInstance(Context context, ProfileManager profileManager, TraktApi traktApi, gb.h0 h0Var, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        return new CatalogRepository(context, profileManager, traktApi, h0Var, cloudSyncInvalidationBus);
    }

    @Override // javax.inject.Provider
    public CatalogRepository get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get(), this.traktApiProvider.get(), this.okHttpClientProvider.get(), this.invalidationBusProvider.get());
    }
}
