package com.arflix.tv.ui.screens.home;

import android.content.Context;
import com.arflix.tv.data.api.InAppYouTubeExtractor;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.ContinueWatchingUpdates;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.RealtimeSyncManager;
import com.arflix.tv.data.repository.SportsRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.TraktSyncService;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.updater.ApkDownloader;
import com.arflix.tv.updater.AppUpdateRepository;
import com.arflix.tv.updater.UpdatePreferences;
import com.arflix.tv.updater.UpdateStatusManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
    private final Provider<ApkDownloader> apkDownloaderProvider;
    private final Provider<AppUpdateRepository> appUpdateRepositoryProvider;
    private final Provider<CatalogRepository> catalogRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ContinueWatchingUpdates> continueWatchingUpdatesProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RealtimeSyncManager> realtimeSyncManagerProvider;
    private final Provider<RemoteSyncManager> remoteSyncManagerProvider;
    private final Provider<SportsRepository> sportsRepositoryProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<TraktSyncService> traktSyncServiceProvider;
    private final Provider<UpdatePreferences> updatePreferencesProvider;
    private final Provider<UpdateStatusManager> updateStatusManagerProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;
    private final Provider<InAppYouTubeExtractor> youTubeExtractorProvider;

    private HomeViewModel_Factory(Provider<MediaRepository> provider, Provider<CatalogRepository> provider2, Provider<StreamRepository> provider3, Provider<SportsRepository> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<TraktSyncService> provider7, Provider<IptvRepository> provider8, Provider<HomeServerRepository> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<CloudSyncRepository> provider12, Provider<LauncherContinueWatchingRepository> provider13, Provider<ContinueWatchingUpdates> provider14, Provider<RealtimeSyncManager> provider15, Provider<ProfileManager> provider16, Provider<AppUpdateRepository> provider17, Provider<ApkDownloader> provider18, Provider<UpdatePreferences> provider19, Provider<UpdateStatusManager> provider20, Provider<InAppYouTubeExtractor> provider21, Provider<Context> provider22) {
        this.mediaRepositoryProvider = provider;
        this.catalogRepositoryProvider = provider2;
        this.streamRepositoryProvider = provider3;
        this.sportsRepositoryProvider = provider4;
        this.traktRepositoryProvider = provider5;
        this.remoteSyncManagerProvider = provider6;
        this.traktSyncServiceProvider = provider7;
        this.iptvRepositoryProvider = provider8;
        this.homeServerRepositoryProvider = provider9;
        this.watchHistoryRepositoryProvider = provider10;
        this.watchlistRepositoryProvider = provider11;
        this.cloudSyncRepositoryProvider = provider12;
        this.launcherContinueWatchingRepositoryProvider = provider13;
        this.continueWatchingUpdatesProvider = provider14;
        this.realtimeSyncManagerProvider = provider15;
        this.profileManagerProvider = provider16;
        this.appUpdateRepositoryProvider = provider17;
        this.apkDownloaderProvider = provider18;
        this.updatePreferencesProvider = provider19;
        this.updateStatusManagerProvider = provider20;
        this.youTubeExtractorProvider = provider21;
        this.contextProvider = provider22;
    }

    public static HomeViewModel_Factory create(Provider<MediaRepository> provider, Provider<CatalogRepository> provider2, Provider<StreamRepository> provider3, Provider<SportsRepository> provider4, Provider<TraktRepository> provider5, Provider<RemoteSyncManager> provider6, Provider<TraktSyncService> provider7, Provider<IptvRepository> provider8, Provider<HomeServerRepository> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<CloudSyncRepository> provider12, Provider<LauncherContinueWatchingRepository> provider13, Provider<ContinueWatchingUpdates> provider14, Provider<RealtimeSyncManager> provider15, Provider<ProfileManager> provider16, Provider<AppUpdateRepository> provider17, Provider<ApkDownloader> provider18, Provider<UpdatePreferences> provider19, Provider<UpdateStatusManager> provider20, Provider<InAppYouTubeExtractor> provider21, Provider<Context> provider22) {
        return new HomeViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22);
    }

    public static HomeViewModel newInstance(MediaRepository mediaRepository, CatalogRepository catalogRepository, StreamRepository streamRepository, SportsRepository sportsRepository, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, TraktSyncService traktSyncService, IptvRepository iptvRepository, HomeServerRepository homeServerRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, ContinueWatchingUpdates continueWatchingUpdates, RealtimeSyncManager realtimeSyncManager, ProfileManager profileManager, AppUpdateRepository appUpdateRepository, ApkDownloader apkDownloader, UpdatePreferences updatePreferences, UpdateStatusManager updateStatusManager, InAppYouTubeExtractor inAppYouTubeExtractor, Context context) {
        return new HomeViewModel(mediaRepository, catalogRepository, streamRepository, sportsRepository, traktRepository, remoteSyncManager, traktSyncService, iptvRepository, homeServerRepository, watchHistoryRepository, watchlistRepository, cloudSyncRepository, launcherContinueWatchingRepository, continueWatchingUpdates, realtimeSyncManager, profileManager, appUpdateRepository, apkDownloader, updatePreferences, updateStatusManager, inAppYouTubeExtractor, context);
    }

    @Override // javax.inject.Provider
    public HomeViewModel get() {
        return newInstance(this.mediaRepositoryProvider.get(), this.catalogRepositoryProvider.get(), this.streamRepositoryProvider.get(), this.sportsRepositoryProvider.get(), this.traktRepositoryProvider.get(), this.remoteSyncManagerProvider.get(), this.traktSyncServiceProvider.get(), this.iptvRepositoryProvider.get(), this.homeServerRepositoryProvider.get(), this.watchHistoryRepositoryProvider.get(), this.watchlistRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get(), this.launcherContinueWatchingRepositoryProvider.get(), this.continueWatchingUpdatesProvider.get(), this.realtimeSyncManagerProvider.get(), this.profileManagerProvider.get(), this.appUpdateRepositoryProvider.get(), this.apkDownloaderProvider.get(), this.updatePreferencesProvider.get(), this.updateStatusManagerProvider.get(), this.youTubeExtractorProvider.get(), this.contextProvider.get());
    }
}
