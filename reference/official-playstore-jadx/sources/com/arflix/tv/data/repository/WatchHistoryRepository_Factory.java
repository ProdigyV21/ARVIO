package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.SupabaseApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class WatchHistoryRepository_Factory implements Factory<WatchHistoryRepository> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RealtimeSyncManager> realtimeSyncManagerProvider;
    private final Provider<SupabaseApi> supabaseApiProvider;

    private WatchHistoryRepository_Factory(Provider<AuthRepository> provider, Provider<SupabaseApi> provider2, Provider<ProfileManager> provider3, Provider<RealtimeSyncManager> provider4) {
        this.authRepositoryProvider = provider;
        this.supabaseApiProvider = provider2;
        this.profileManagerProvider = provider3;
        this.realtimeSyncManagerProvider = provider4;
    }

    public static WatchHistoryRepository_Factory create(Provider<AuthRepository> provider, Provider<SupabaseApi> provider2, Provider<ProfileManager> provider3, Provider<RealtimeSyncManager> provider4) {
        return new WatchHistoryRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static WatchHistoryRepository newInstance(javax.inject.Provider<AuthRepository> provider, SupabaseApi supabaseApi, ProfileManager profileManager, javax.inject.Provider<RealtimeSyncManager> provider2) {
        return new WatchHistoryRepository(provider, supabaseApi, profileManager, provider2);
    }

    @Override // javax.inject.Provider
    public WatchHistoryRepository get() {
        return newInstance(this.authRepositoryProvider, this.supabaseApiProvider.get(), this.profileManagerProvider.get(), this.realtimeSyncManagerProvider);
    }
}
