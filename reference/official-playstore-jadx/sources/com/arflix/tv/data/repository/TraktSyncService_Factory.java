package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TraktSyncService_Factory implements Factory<TraktSyncService> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<MdbListRepository> mdbListRepositoryProvider;
    private final Provider<TraktOutboxRepository> outboxRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<SupabaseApi> supabaseApiProvider;
    private final Provider<SyncProviderStore> syncProviderStoreProvider;
    private final Provider<TraktApi> traktApiProvider;

    private TraktSyncService_Factory(Provider<Context> provider, Provider<TraktApi> provider2, Provider<SupabaseApi> provider3, Provider<AuthRepository> provider4, Provider<TraktOutboxRepository> provider5, Provider<ProfileManager> provider6, Provider<MdbListRepository> provider7, Provider<SyncProviderStore> provider8) {
        this.contextProvider = provider;
        this.traktApiProvider = provider2;
        this.supabaseApiProvider = provider3;
        this.authRepositoryProvider = provider4;
        this.outboxRepositoryProvider = provider5;
        this.profileManagerProvider = provider6;
        this.mdbListRepositoryProvider = provider7;
        this.syncProviderStoreProvider = provider8;
    }

    public static TraktSyncService_Factory create(Provider<Context> provider, Provider<TraktApi> provider2, Provider<SupabaseApi> provider3, Provider<AuthRepository> provider4, Provider<TraktOutboxRepository> provider5, Provider<ProfileManager> provider6, Provider<MdbListRepository> provider7, Provider<SyncProviderStore> provider8) {
        return new TraktSyncService_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static TraktSyncService newInstance(Context context, TraktApi traktApi, SupabaseApi supabaseApi, AuthRepository authRepository, TraktOutboxRepository traktOutboxRepository, ProfileManager profileManager, MdbListRepository mdbListRepository, SyncProviderStore syncProviderStore) {
        return new TraktSyncService(context, traktApi, supabaseApi, authRepository, traktOutboxRepository, profileManager, mdbListRepository, syncProviderStore);
    }

    @Override // javax.inject.Provider
    public TraktSyncService get() {
        return newInstance(this.contextProvider.get(), this.traktApiProvider.get(), this.supabaseApiProvider.get(), this.authRepositoryProvider.get(), this.outboxRepositoryProvider.get(), this.profileManagerProvider.get(), this.mdbListRepositoryProvider.get(), this.syncProviderStoreProvider.get());
    }
}
