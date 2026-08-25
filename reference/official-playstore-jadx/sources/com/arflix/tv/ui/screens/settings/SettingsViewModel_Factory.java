package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.CatalogDiscoveryRepository;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MdbListRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.TraktSyncService;
import com.arflix.tv.data.repository.TvDeviceAuthRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import com.arflix.tv.updater.ApkDownloader;
import com.arflix.tv.updater.AppUpdateRepository;
import com.arflix.tv.updater.UpdatePreferences;
import com.arflix.tv.updater.UpdateStatusManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
    private final Provider<ApkDownloader> apkDownloaderProvider;
    private final Provider<AppUpdateRepository> appUpdateRepositoryProvider;
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CatalogDiscoveryRepository> catalogDiscoveryRepositoryProvider;
    private final Provider<CatalogRepository> catalogRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
    private final Provider<MdbListRepository> mdbListRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<ProfileRepository> profileRepositoryProvider;
    private final Provider<SimklAuthManager> simklAuthManagerProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<SyncProviderStore> syncProviderStoreProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<TraktSyncService> traktSyncServiceProvider;
    private final Provider<TvDeviceAuthRepository> tvDeviceAuthRepositoryProvider;
    private final Provider<UpdatePreferences> updatePreferencesProvider;
    private final Provider<UpdateStatusManager> updateStatusManagerProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private SettingsViewModel_Factory(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktRepository> provider3, Provider<StreamRepository> provider4, Provider<MediaRepository> provider5, Provider<CatalogRepository> provider6, Provider<CatalogDiscoveryRepository> provider7, Provider<IptvRepository> provider8, Provider<HomeServerRepository> provider9, Provider<WatchlistRepository> provider10, Provider<AuthRepository> provider11, Provider<ProfileRepository> provider12, Provider<TvDeviceAuthRepository> provider13, Provider<TraktSyncService> provider14, Provider<CloudSyncRepository> provider15, Provider<LauncherContinueWatchingRepository> provider16, Provider<AppUpdateRepository> provider17, Provider<UpdatePreferences> provider18, Provider<ApkDownloader> provider19, Provider<UpdateStatusManager> provider20, Provider<MdbListRepository> provider21, Provider<SyncProviderStore> provider22, Provider<WatchHistoryRepository> provider23, Provider<SimklAuthManager> provider24) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
        this.traktRepositoryProvider = provider3;
        this.streamRepositoryProvider = provider4;
        this.mediaRepositoryProvider = provider5;
        this.catalogRepositoryProvider = provider6;
        this.catalogDiscoveryRepositoryProvider = provider7;
        this.iptvRepositoryProvider = provider8;
        this.homeServerRepositoryProvider = provider9;
        this.watchlistRepositoryProvider = provider10;
        this.authRepositoryProvider = provider11;
        this.profileRepositoryProvider = provider12;
        this.tvDeviceAuthRepositoryProvider = provider13;
        this.traktSyncServiceProvider = provider14;
        this.cloudSyncRepositoryProvider = provider15;
        this.launcherContinueWatchingRepositoryProvider = provider16;
        this.appUpdateRepositoryProvider = provider17;
        this.updatePreferencesProvider = provider18;
        this.apkDownloaderProvider = provider19;
        this.updateStatusManagerProvider = provider20;
        this.mdbListRepositoryProvider = provider21;
        this.syncProviderStoreProvider = provider22;
        this.watchHistoryRepositoryProvider = provider23;
        this.simklAuthManagerProvider = provider24;
    }

    public static SettingsViewModel_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktRepository> provider3, Provider<StreamRepository> provider4, Provider<MediaRepository> provider5, Provider<CatalogRepository> provider6, Provider<CatalogDiscoveryRepository> provider7, Provider<IptvRepository> provider8, Provider<HomeServerRepository> provider9, Provider<WatchlistRepository> provider10, Provider<AuthRepository> provider11, Provider<ProfileRepository> provider12, Provider<TvDeviceAuthRepository> provider13, Provider<TraktSyncService> provider14, Provider<CloudSyncRepository> provider15, Provider<LauncherContinueWatchingRepository> provider16, Provider<AppUpdateRepository> provider17, Provider<UpdatePreferences> provider18, Provider<ApkDownloader> provider19, Provider<UpdateStatusManager> provider20, Provider<MdbListRepository> provider21, Provider<SyncProviderStore> provider22, Provider<WatchHistoryRepository> provider23, Provider<SimklAuthManager> provider24) {
        return new SettingsViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19, provider20, provider21, provider22, provider23, provider24);
    }

    public static SettingsViewModel newInstance(Context context, ProfileManager profileManager, TraktRepository traktRepository, StreamRepository streamRepository, MediaRepository mediaRepository, CatalogRepository catalogRepository, CatalogDiscoveryRepository catalogDiscoveryRepository, IptvRepository iptvRepository, HomeServerRepository homeServerRepository, WatchlistRepository watchlistRepository, AuthRepository authRepository, ProfileRepository profileRepository, TvDeviceAuthRepository tvDeviceAuthRepository, TraktSyncService traktSyncService, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, AppUpdateRepository appUpdateRepository, UpdatePreferences updatePreferences, ApkDownloader apkDownloader, UpdateStatusManager updateStatusManager, MdbListRepository mdbListRepository, SyncProviderStore syncProviderStore, WatchHistoryRepository watchHistoryRepository, SimklAuthManager simklAuthManager) {
        return new SettingsViewModel(context, profileManager, traktRepository, streamRepository, mediaRepository, catalogRepository, catalogDiscoveryRepository, iptvRepository, homeServerRepository, watchlistRepository, authRepository, profileRepository, tvDeviceAuthRepository, traktSyncService, cloudSyncRepository, launcherContinueWatchingRepository, appUpdateRepository, updatePreferences, apkDownloader, updateStatusManager, mdbListRepository, syncProviderStore, watchHistoryRepository, simklAuthManager);
    }

    @Override // javax.inject.Provider
    public SettingsViewModel get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get(), this.traktRepositoryProvider.get(), this.streamRepositoryProvider.get(), this.mediaRepositoryProvider.get(), this.catalogRepositoryProvider.get(), this.catalogDiscoveryRepositoryProvider.get(), this.iptvRepositoryProvider.get(), this.homeServerRepositoryProvider.get(), this.watchlistRepositoryProvider.get(), this.authRepositoryProvider.get(), this.profileRepositoryProvider.get(), this.tvDeviceAuthRepositoryProvider.get(), this.traktSyncServiceProvider.get(), this.cloudSyncRepositoryProvider.get(), this.launcherContinueWatchingRepositoryProvider.get(), this.appUpdateRepositoryProvider.get(), this.updatePreferencesProvider.get(), this.apkDownloaderProvider.get(), this.updateStatusManagerProvider.get(), this.mdbListRepositoryProvider.get(), this.syncProviderStoreProvider.get(), this.watchHistoryRepositoryProvider.get(), this.simklAuthManagerProvider.get());
    }
}
