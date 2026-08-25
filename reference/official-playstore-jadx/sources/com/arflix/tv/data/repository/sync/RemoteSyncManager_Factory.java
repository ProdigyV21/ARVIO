package com.arflix.tv.data.repository.sync;

import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class RemoteSyncManager_Factory implements Factory<RemoteSyncManager> {
    private final Provider<MdbListRemoteProvider> mdbListProvider;
    private final Provider<SimklRemoteProvider> simklProvider;
    private final Provider<SyncProviderStore> storeProvider;
    private final Provider<TraktRemoteProvider> traktProvider;

    private RemoteSyncManager_Factory(Provider<SyncProviderStore> provider, Provider<TraktRemoteProvider> provider2, Provider<MdbListRemoteProvider> provider3, Provider<SimklRemoteProvider> provider4) {
        this.storeProvider = provider;
        this.traktProvider = provider2;
        this.mdbListProvider = provider3;
        this.simklProvider = provider4;
    }

    public static RemoteSyncManager_Factory create(Provider<SyncProviderStore> provider, Provider<TraktRemoteProvider> provider2, Provider<MdbListRemoteProvider> provider3, Provider<SimklRemoteProvider> provider4) {
        return new RemoteSyncManager_Factory(provider, provider2, provider3, provider4);
    }

    public static RemoteSyncManager newInstance(SyncProviderStore syncProviderStore, TraktRemoteProvider traktRemoteProvider, MdbListRemoteProvider mdbListRemoteProvider, SimklRemoteProvider simklRemoteProvider) {
        return new RemoteSyncManager(syncProviderStore, traktRemoteProvider, mdbListRemoteProvider, simklRemoteProvider);
    }

    @Override // javax.inject.Provider
    public RemoteSyncManager get() {
        return newInstance(this.storeProvider.get(), this.traktProvider.get(), this.mdbListProvider.get(), this.simklProvider.get());
    }
}
