package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SimklAuthManager_Factory implements Factory<SimklAuthManager> {
    private final Provider<SimklApi> simklApiProvider;
    private final Provider<SyncProviderStore> syncProviderStoreProvider;

    private SimklAuthManager_Factory(Provider<SimklApi> provider, Provider<SyncProviderStore> provider2) {
        this.simklApiProvider = provider;
        this.syncProviderStoreProvider = provider2;
    }

    public static SimklAuthManager_Factory create(Provider<SimklApi> provider, Provider<SyncProviderStore> provider2) {
        return new SimklAuthManager_Factory(provider, provider2);
    }

    public static SimklAuthManager newInstance(SimklApi simklApi, SyncProviderStore syncProviderStore) {
        return new SimklAuthManager(simklApi, syncProviderStore);
    }

    @Override // javax.inject.Provider
    public SimklAuthManager get() {
        return newInstance(this.simklApiProvider.get(), this.syncProviderStoreProvider.get());
    }
}
