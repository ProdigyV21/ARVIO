package com.arflix.tv.ui.screens.details;

import android.content.Context;
import com.arflix.tv.core.plugin.PluginManager;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.util.AnimeMapper;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class DetailsViewModel_Factory implements Factory<DetailsViewModel> {
    private final Provider<AnimeMapper> animeMapperProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<PluginManager> pluginManagerProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RemoteSyncManager> remoteSyncManagerProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<TmdbApi> tmdbApiProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private DetailsViewModel_Factory(Provider<Context> provider, Provider<MediaRepository> provider2, Provider<PluginManager> provider3, Provider<ProfileManager> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<StreamRepository> provider7, Provider<AnimeMapper> provider8, Provider<TmdbApi> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<CloudSyncRepository> provider12, Provider<LauncherContinueWatchingRepository> provider13) {
        this.contextProvider = provider;
        this.mediaRepositoryProvider = provider2;
        this.pluginManagerProvider = provider3;
        this.profileManagerProvider = provider4;
        this.traktRepositoryProvider = provider5;
        this.remoteSyncManagerProvider = provider6;
        this.streamRepositoryProvider = provider7;
        this.animeMapperProvider = provider8;
        this.tmdbApiProvider = provider9;
        this.watchHistoryRepositoryProvider = provider10;
        this.watchlistRepositoryProvider = provider11;
        this.cloudSyncRepositoryProvider = provider12;
        this.launcherContinueWatchingRepositoryProvider = provider13;
    }

    public static DetailsViewModel_Factory create(Provider<Context> provider, Provider<MediaRepository> provider2, Provider<PluginManager> provider3, Provider<ProfileManager> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<StreamRepository> provider7, Provider<AnimeMapper> provider8, Provider<TmdbApi> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<CloudSyncRepository> provider12, Provider<LauncherContinueWatchingRepository> provider13) {
        return new DetailsViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static DetailsViewModel newInstance(Context context, MediaRepository mediaRepository, PluginManager pluginManager, ProfileManager profileManager, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, StreamRepository streamRepository, AnimeMapper animeMapper, TmdbApi tmdbApi, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository) {
        return new DetailsViewModel(context, mediaRepository, pluginManager, profileManager, traktRepository, remoteSyncManager, streamRepository, animeMapper, tmdbApi, watchHistoryRepository, watchlistRepository, cloudSyncRepository, launcherContinueWatchingRepository);
    }

    @Override // javax.inject.Provider
    public DetailsViewModel get() {
        return newInstance(this.contextProvider.get(), this.mediaRepositoryProvider.get(), this.pluginManagerProvider.get(), this.profileManagerProvider.get(), this.traktRepositoryProvider.get(), this.remoteSyncManagerProvider.get(), this.streamRepositoryProvider.get(), this.animeMapperProvider.get(), this.tmdbApiProvider.get(), this.watchHistoryRepositoryProvider.get(), this.watchlistRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get(), this.launcherContinueWatchingRepositoryProvider.get());
    }
}
