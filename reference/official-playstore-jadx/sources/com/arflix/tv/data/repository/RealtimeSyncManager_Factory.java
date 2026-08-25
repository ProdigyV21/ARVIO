package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class RealtimeSyncManager_Factory implements Factory<RealtimeSyncManager> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;

    private RealtimeSyncManager_Factory(Provider<Context> provider, Provider<CloudSyncRepository> provider2, Provider<AuthRepository> provider3) {
        this.contextProvider = provider;
        this.cloudSyncRepositoryProvider = provider2;
        this.authRepositoryProvider = provider3;
    }

    public static RealtimeSyncManager_Factory create(Provider<Context> provider, Provider<CloudSyncRepository> provider2, Provider<AuthRepository> provider3) {
        return new RealtimeSyncManager_Factory(provider, provider2, provider3);
    }

    public static RealtimeSyncManager newInstance(Context context, CloudSyncRepository cloudSyncRepository, AuthRepository authRepository) {
        return new RealtimeSyncManager(context, cloudSyncRepository, authRepository);
    }

    @Override // javax.inject.Provider
    public RealtimeSyncManager get() {
        return newInstance(this.contextProvider.get(), this.cloudSyncRepositoryProvider.get(), this.authRepositoryProvider.get());
    }
}
