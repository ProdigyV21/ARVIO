package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class WatchlistRepository_Factory implements Factory<WatchlistRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<TmdbApi> tmdbApiProvider;

    private WatchlistRepository_Factory(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TmdbApi> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
        this.tmdbApiProvider = provider3;
        this.invalidationBusProvider = provider4;
    }

    public static WatchlistRepository_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TmdbApi> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        return new WatchlistRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static WatchlistRepository newInstance(Context context, ProfileManager profileManager, TmdbApi tmdbApi, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        return new WatchlistRepository(context, profileManager, tmdbApi, cloudSyncInvalidationBus);
    }

    @Override // javax.inject.Provider
    public WatchlistRepository get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get(), this.tmdbApiProvider.get(), this.invalidationBusProvider.get());
    }
}
