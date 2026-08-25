package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.api.SimklApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SimklScrobbler_Factory implements Factory<SimklScrobbler> {
    private final Provider<SimklAuthManager> authManagerProvider;
    private final Provider<SimklApi> simklApiProvider;

    private SimklScrobbler_Factory(Provider<SimklApi> provider, Provider<SimklAuthManager> provider2) {
        this.simklApiProvider = provider;
        this.authManagerProvider = provider2;
    }

    public static SimklScrobbler_Factory create(Provider<SimklApi> provider, Provider<SimklAuthManager> provider2) {
        return new SimklScrobbler_Factory(provider, provider2);
    }

    public static SimklScrobbler newInstance(SimklApi simklApi, SimklAuthManager simklAuthManager) {
        return new SimklScrobbler(simklApi, simklAuthManager);
    }

    @Override // javax.inject.Provider
    public SimklScrobbler get() {
        return newInstance(this.simklApiProvider.get(), this.authManagerProvider.get());
    }
}
