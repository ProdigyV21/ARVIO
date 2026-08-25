package com.arflix.tv.ui.screens.player;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.PlaybackTelemetryRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.SkipIntroRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.util.AnimeMapper;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
    private final Provider<AnimeMapper> animeMapperProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<PlaybackTelemetryRepository> playbackTelemetryRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RemoteSyncManager> remoteSyncManagerProvider;
    private final Provider<SkipIntroRepository> skipIntroRepositoryProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<TmdbApi> tmdbApiProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;

    private PlayerViewModel_Factory(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<MediaRepository> provider3, Provider<StreamRepository> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<WatchHistoryRepository> provider7, Provider<CloudSyncRepository> provider8, Provider<LauncherContinueWatchingRepository> provider9, Provider<AnimeMapper> provider10, Provider<TmdbApi> provider11, Provider<SkipIntroRepository> provider12, Provider<PlaybackTelemetryRepository> provider13) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
        this.mediaRepositoryProvider = provider3;
        this.streamRepositoryProvider = provider4;
        this.traktRepositoryProvider = provider5;
        this.remoteSyncManagerProvider = provider6;
        this.watchHistoryRepositoryProvider = provider7;
        this.cloudSyncRepositoryProvider = provider8;
        this.launcherContinueWatchingRepositoryProvider = provider9;
        this.animeMapperProvider = provider10;
        this.tmdbApiProvider = provider11;
        this.skipIntroRepositoryProvider = provider12;
        this.playbackTelemetryRepositoryProvider = provider13;
    }

    public static PlayerViewModel_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<MediaRepository> provider3, Provider<StreamRepository> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<WatchHistoryRepository> provider7, Provider<CloudSyncRepository> provider8, Provider<LauncherContinueWatchingRepository> provider9, Provider<AnimeMapper> provider10, Provider<TmdbApi> provider11, Provider<SkipIntroRepository> provider12, Provider<PlaybackTelemetryRepository> provider13) {
        return new PlayerViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13);
    }

    public static PlayerViewModel newInstance(Context context, ProfileManager profileManager, MediaRepository mediaRepository, StreamRepository streamRepository, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, WatchHistoryRepository watchHistoryRepository, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, AnimeMapper animeMapper, TmdbApi tmdbApi, SkipIntroRepository skipIntroRepository, PlaybackTelemetryRepository playbackTelemetryRepository) {
        return new PlayerViewModel(context, profileManager, mediaRepository, streamRepository, traktRepository, remoteSyncManager, watchHistoryRepository, cloudSyncRepository, launcherContinueWatchingRepository, animeMapper, tmdbApi, skipIntroRepository, playbackTelemetryRepository);
    }

    @Override // javax.inject.Provider
    public PlayerViewModel get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get(), this.mediaRepositoryProvider.get(), this.streamRepositoryProvider.get(), this.traktRepositoryProvider.get(), this.remoteSyncManagerProvider.get(), this.watchHistoryRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get(), this.launcherContinueWatchingRepositoryProvider.get(), this.animeMapperProvider.get(), this.tmdbApiProvider.get(), this.skipIntroRepositoryProvider.get(), this.playbackTelemetryRepositoryProvider.get());
    }
}
