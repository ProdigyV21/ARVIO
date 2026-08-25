package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SimklSyncService_Factory implements Factory<SimklSyncService> {
    private final Provider<SimklAuthManager> authManagerProvider;
    private final Provider<SimklApi> simklApiProvider;
    private final Provider<TmdbApi> tmdbApiProvider;

    private SimklSyncService_Factory(Provider<SimklApi> provider, Provider<SimklAuthManager> provider2, Provider<TmdbApi> provider3) {
        this.simklApiProvider = provider;
        this.authManagerProvider = provider2;
        this.tmdbApiProvider = provider3;
    }

    public static SimklSyncService_Factory create(Provider<SimklApi> provider, Provider<SimklAuthManager> provider2, Provider<TmdbApi> provider3) {
        return new SimklSyncService_Factory(provider, provider2, provider3);
    }

    public static SimklSyncService newInstance(SimklApi simklApi, SimklAuthManager simklAuthManager, TmdbApi tmdbApi) {
        return new SimklSyncService(simklApi, simklAuthManager, tmdbApi);
    }

    @Override // javax.inject.Provider
    public SimklSyncService get() {
        return newInstance(this.simklApiProvider.get(), this.authManagerProvider.get(), this.tmdbApiProvider.get());
    }
}
