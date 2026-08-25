package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.simkl.SimklScrobbler;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SimklRemoteProvider_Factory implements Factory<SimklRemoteProvider> {
    private final Provider<SimklAuthManager> authManagerProvider;
    private final Provider<SimklScrobbler> scrobblerProvider;
    private final Provider<SimklSyncService> syncServiceProvider;

    private SimklRemoteProvider_Factory(Provider<SimklAuthManager> provider, Provider<SimklScrobbler> provider2, Provider<SimklSyncService> provider3) {
        this.authManagerProvider = provider;
        this.scrobblerProvider = provider2;
        this.syncServiceProvider = provider3;
    }

    public static SimklRemoteProvider_Factory create(Provider<SimklAuthManager> provider, Provider<SimklScrobbler> provider2, Provider<SimklSyncService> provider3) {
        return new SimklRemoteProvider_Factory(provider, provider2, provider3);
    }

    public static SimklRemoteProvider newInstance(SimklAuthManager simklAuthManager, SimklScrobbler simklScrobbler, SimklSyncService simklSyncService) {
        return new SimklRemoteProvider(simklAuthManager, simklScrobbler, simklSyncService);
    }

    @Override // javax.inject.Provider
    public SimklRemoteProvider get() {
        return newInstance(this.authManagerProvider.get(), this.scrobblerProvider.get(), this.syncServiceProvider.get());
    }
}
