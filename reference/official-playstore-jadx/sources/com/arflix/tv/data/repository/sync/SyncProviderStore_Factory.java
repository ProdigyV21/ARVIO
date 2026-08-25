package com.arflix.tv.data.repository.sync;

import android.content.Context;
import com.arflix.tv.data.repository.ProfileManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SyncProviderStore_Factory implements Factory<SyncProviderStore> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileManager> profileManagerProvider;

    private SyncProviderStore_Factory(Provider<Context> provider, Provider<ProfileManager> provider2) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
    }

    public static SyncProviderStore_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2) {
        return new SyncProviderStore_Factory(provider, provider2);
    }

    public static SyncProviderStore newInstance(Context context, ProfileManager profileManager) {
        return new SyncProviderStore(context, profileManager);
    }

    @Override // javax.inject.Provider
    public SyncProviderStore get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get());
    }
}
