package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.MdbListApi;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class MdbListRepository_Factory implements Factory<MdbListRepository> {
    private final Provider<MdbListApi> apiProvider;
    private final Provider<SyncProviderStore> storeProvider;

    private MdbListRepository_Factory(Provider<MdbListApi> provider, Provider<SyncProviderStore> provider2) {
        this.apiProvider = provider;
        this.storeProvider = provider2;
    }

    public static MdbListRepository_Factory create(Provider<MdbListApi> provider, Provider<SyncProviderStore> provider2) {
        return new MdbListRepository_Factory(provider, provider2);
    }

    public static MdbListRepository newInstance(MdbListApi mdbListApi, SyncProviderStore syncProviderStore) {
        return new MdbListRepository(mdbListApi, syncProviderStore);
    }

    @Override // javax.inject.Provider
    public MdbListRepository get() {
        return newInstance(this.apiProvider.get(), this.storeProvider.get());
    }
}
