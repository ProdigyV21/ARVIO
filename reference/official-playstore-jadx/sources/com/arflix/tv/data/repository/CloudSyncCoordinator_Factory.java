package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CloudSyncCoordinator_Factory implements Factory<CloudSyncCoordinator> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;

    private CloudSyncCoordinator_Factory(Provider<Context> provider, Provider<CloudSyncInvalidationBus> provider2, Provider<CloudSyncRepository> provider3, Provider<AuthRepository> provider4) {
        this.contextProvider = provider;
        this.invalidationBusProvider = provider2;
        this.cloudSyncRepositoryProvider = provider3;
        this.authRepositoryProvider = provider4;
    }

    public static CloudSyncCoordinator_Factory create(Provider<Context> provider, Provider<CloudSyncInvalidationBus> provider2, Provider<CloudSyncRepository> provider3, Provider<AuthRepository> provider4) {
        return new CloudSyncCoordinator_Factory(provider, provider2, provider3, provider4);
    }

    public static CloudSyncCoordinator newInstance(Context context, CloudSyncInvalidationBus cloudSyncInvalidationBus, CloudSyncRepository cloudSyncRepository, AuthRepository authRepository) {
        return new CloudSyncCoordinator(context, cloudSyncInvalidationBus, cloudSyncRepository, authRepository);
    }

    @Override // javax.inject.Provider
    public CloudSyncCoordinator get() {
        return newInstance(this.contextProvider.get(), this.invalidationBusProvider.get(), this.cloudSyncRepositoryProvider.get(), this.authRepositoryProvider.get());
    }
}
