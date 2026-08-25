package com.arflix.tv.ui.screens.watchlist;

import android.content.Context;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class WatchlistViewModel_Factory implements Factory<WatchlistViewModel> {
    private final Provider<CatalogRepository> catalogRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RemoteSyncManager> remoteSyncManagerProvider;
    private final Provider<SimklAuthManager> simklAuthManagerProvider;
    private final Provider<SimklSyncService> simklSyncServiceProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private WatchlistViewModel_Factory(Provider<Context> provider, Provider<WatchlistRepository> provider2, Provider<CloudSyncRepository> provider3, Provider<TraktRepository> provider4, Provider<RemoteSyncManager> provider5, Provider<MediaRepository> provider6, Provider<HomeServerRepository> provider7, Provider<CatalogRepository> provider8, Provider<WatchHistoryRepository> provider9, Provider<SimklAuthManager> provider10, Provider<SimklSyncService> provider11, Provider<ProfileManager> provider12) {
        this.contextProvider = provider;
        this.watchlistRepositoryProvider = provider2;
        this.cloudSyncRepositoryProvider = provider3;
        this.traktRepositoryProvider = provider4;
        this.remoteSyncManagerProvider = provider5;
        this.mediaRepositoryProvider = provider6;
        this.homeServerRepositoryProvider = provider7;
        this.catalogRepositoryProvider = provider8;
        this.watchHistoryRepositoryProvider = provider9;
        this.simklAuthManagerProvider = provider10;
        this.simklSyncServiceProvider = provider11;
        this.profileManagerProvider = provider12;
    }

    public static WatchlistViewModel_Factory create(Provider<Context> provider, Provider<WatchlistRepository> provider2, Provider<CloudSyncRepository> provider3, Provider<TraktRepository> provider4, Provider<RemoteSyncManager> provider5, Provider<MediaRepository> provider6, Provider<HomeServerRepository> provider7, Provider<CatalogRepository> provider8, Provider<WatchHistoryRepository> provider9, Provider<SimklAuthManager> provider10, Provider<SimklSyncService> provider11, Provider<ProfileManager> provider12) {
        return new WatchlistViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static WatchlistViewModel newInstance(Context context, WatchlistRepository watchlistRepository, CloudSyncRepository cloudSyncRepository, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, MediaRepository mediaRepository, HomeServerRepository homeServerRepository, CatalogRepository catalogRepository, WatchHistoryRepository watchHistoryRepository, SimklAuthManager simklAuthManager, SimklSyncService simklSyncService, ProfileManager profileManager) {
        return new WatchlistViewModel(context, watchlistRepository, cloudSyncRepository, traktRepository, remoteSyncManager, mediaRepository, homeServerRepository, catalogRepository, watchHistoryRepository, simklAuthManager, simklSyncService, profileManager);
    }

    @Override // javax.inject.Provider
    public WatchlistViewModel get() {
        return newInstance(this.contextProvider.get(), this.watchlistRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get(), this.traktRepositoryProvider.get(), this.remoteSyncManagerProvider.get(), this.mediaRepositoryProvider.get(), this.homeServerRepositoryProvider.get(), this.catalogRepositoryProvider.get(), this.watchHistoryRepositoryProvider.get(), this.simklAuthManagerProvider.get(), this.simklSyncServiceProvider.get(), this.profileManagerProvider.get());
    }
}
