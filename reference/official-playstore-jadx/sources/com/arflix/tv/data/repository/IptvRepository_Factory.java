package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class IptvRepository_Factory implements Factory<IptvRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;

    private IptvRepository_Factory(Provider<Context> provider, Provider<gb.h0> provider2, Provider<ProfileManager> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.profileManagerProvider = provider3;
        this.invalidationBusProvider = provider4;
    }

    public static IptvRepository_Factory create(Provider<Context> provider, Provider<gb.h0> provider2, Provider<ProfileManager> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        return new IptvRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static IptvRepository newInstance(Context context, gb.h0 h0Var, ProfileManager profileManager, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        return new IptvRepository(context, h0Var, profileManager, cloudSyncInvalidationBus);
    }

    @Override // javax.inject.Provider
    public IptvRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get(), this.profileManagerProvider.get(), this.invalidationBusProvider.get());
    }
}
