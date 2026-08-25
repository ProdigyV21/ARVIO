package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TraktRepository_Factory implements Factory<TraktRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<ContinueWatchingUpdates> continueWatchingUpdatesProvider;
    private final Provider<MdbListRepository> mdbListRepositoryProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<SimklSyncService> simklSyncServiceProvider;
    private final Provider<SyncProviderStore> syncProviderStoreProvider;
    private final Provider<TraktSyncService> syncServiceProvider;
    private final Provider<TmdbApi> tmdbApiProvider;
    private final Provider<TraktApi> traktApiProvider;

    private TraktRepository_Factory(Provider<Context> provider, Provider<TraktApi> provider2, Provider<TmdbApi> provider3, Provider<gb.h0> provider4, Provider<TraktSyncService> provider5, Provider<ProfileManager> provider6, Provider<MdbListRepository> provider7, Provider<SyncProviderStore> provider8, Provider<SimklSyncService> provider9, Provider<ContinueWatchingUpdates> provider10) {
        this.contextProvider = provider;
        this.traktApiProvider = provider2;
        this.tmdbApiProvider = provider3;
        this.okHttpClientProvider = provider4;
        this.syncServiceProvider = provider5;
        this.profileManagerProvider = provider6;
        this.mdbListRepositoryProvider = provider7;
        this.syncProviderStoreProvider = provider8;
        this.simklSyncServiceProvider = provider9;
        this.continueWatchingUpdatesProvider = provider10;
    }

    public static TraktRepository_Factory create(Provider<Context> provider, Provider<TraktApi> provider2, Provider<TmdbApi> provider3, Provider<gb.h0> provider4, Provider<TraktSyncService> provider5, Provider<ProfileManager> provider6, Provider<MdbListRepository> provider7, Provider<SyncProviderStore> provider8, Provider<SimklSyncService> provider9, Provider<ContinueWatchingUpdates> provider10) {
        return new TraktRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static TraktRepository newInstance(Context context, TraktApi traktApi, TmdbApi tmdbApi, gb.h0 h0Var, javax.inject.Provider<TraktSyncService> provider, ProfileManager profileManager, MdbListRepository mdbListRepository, SyncProviderStore syncProviderStore, SimklSyncService simklSyncService, ContinueWatchingUpdates continueWatchingUpdates) {
        return new TraktRepository(context, traktApi, tmdbApi, h0Var, provider, profileManager, mdbListRepository, syncProviderStore, simklSyncService, continueWatchingUpdates);
    }

    @Override // javax.inject.Provider
    public TraktRepository get() {
        return newInstance(this.contextProvider.get(), this.traktApiProvider.get(), this.tmdbApiProvider.get(), this.okHttpClientProvider.get(), this.syncServiceProvider, this.profileManagerProvider.get(), this.mdbListRepositoryProvider.get(), this.syncProviderStoreProvider.get(), this.simklSyncServiceProvider.get(), this.continueWatchingUpdatesProvider.get());
    }
}
