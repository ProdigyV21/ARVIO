package com.arflix.tv;

import com.arflix.tv.data.repository.AppUsageAnalyticsRepository;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.CloudSyncCoordinator;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.RealtimeSyncManager;
import com.arflix.tv.data.repository.WatchlistRepository;
import dagger.MembersInjector;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ArflixApplication_MembersInjector implements MembersInjector<ArflixApplication> {
    private final Provider<AppUsageAnalyticsRepository> appUsageAnalyticsRepositoryProvider;
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CloudSyncCoordinator> cloudSyncCoordinatorProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RealtimeSyncManager> realtimeSyncManagerProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;
    private final Provider<i1.b> workerFactoryProvider;

    private ArflixApplication_MembersInjector(Provider<i1.b> provider, Provider<ProfileManager> provider2, Provider<AuthRepository> provider3, Provider<CloudSyncRepository> provider4, Provider<CloudSyncCoordinator> provider5, Provider<RealtimeSyncManager> provider6, Provider<WatchlistRepository> provider7, Provider<AppUsageAnalyticsRepository> provider8) {
        this.workerFactoryProvider = provider;
        this.profileManagerProvider = provider2;
        this.authRepositoryProvider = provider3;
        this.cloudSyncRepositoryProvider = provider4;
        this.cloudSyncCoordinatorProvider = provider5;
        this.realtimeSyncManagerProvider = provider6;
        this.watchlistRepositoryProvider = provider7;
        this.appUsageAnalyticsRepositoryProvider = provider8;
    }

    public static MembersInjector<ArflixApplication> create(Provider<i1.b> provider, Provider<ProfileManager> provider2, Provider<AuthRepository> provider3, Provider<CloudSyncRepository> provider4, Provider<CloudSyncCoordinator> provider5, Provider<RealtimeSyncManager> provider6, Provider<WatchlistRepository> provider7, Provider<AppUsageAnalyticsRepository> provider8) {
        return new ArflixApplication_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectAppUsageAnalyticsRepository(ArflixApplication arflixApplication, AppUsageAnalyticsRepository appUsageAnalyticsRepository) {
        arflixApplication.appUsageAnalyticsRepository = appUsageAnalyticsRepository;
    }

    public static void injectAuthRepository(ArflixApplication arflixApplication, AuthRepository authRepository) {
        arflixApplication.authRepository = authRepository;
    }

    public static void injectCloudSyncCoordinator(ArflixApplication arflixApplication, CloudSyncCoordinator cloudSyncCoordinator) {
        arflixApplication.cloudSyncCoordinator = cloudSyncCoordinator;
    }

    public static void injectCloudSyncRepository(ArflixApplication arflixApplication, CloudSyncRepository cloudSyncRepository) {
        arflixApplication.cloudSyncRepository = cloudSyncRepository;
    }

    public static void injectProfileManager(ArflixApplication arflixApplication, ProfileManager profileManager) {
        arflixApplication.profileManager = profileManager;
    }

    public static void injectRealtimeSyncManager(ArflixApplication arflixApplication, RealtimeSyncManager realtimeSyncManager) {
        arflixApplication.realtimeSyncManager = realtimeSyncManager;
    }

    public static void injectWatchlistRepository(ArflixApplication arflixApplication, WatchlistRepository watchlistRepository) {
        arflixApplication.watchlistRepository = watchlistRepository;
    }

    public static void injectWorkerFactory(ArflixApplication arflixApplication, i1.b bVar) {
        arflixApplication.workerFactory = bVar;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ArflixApplication arflixApplication) {
        injectWorkerFactory(arflixApplication, this.workerFactoryProvider.get());
        injectProfileManager(arflixApplication, this.profileManagerProvider.get());
        injectAuthRepository(arflixApplication, this.authRepositoryProvider.get());
        injectCloudSyncRepository(arflixApplication, this.cloudSyncRepositoryProvider.get());
        injectCloudSyncCoordinator(arflixApplication, this.cloudSyncCoordinatorProvider.get());
        injectRealtimeSyncManager(arflixApplication, this.realtimeSyncManagerProvider.get());
        injectWatchlistRepository(arflixApplication, this.watchlistRepositoryProvider.get());
        injectAppUsageAnalyticsRepository(arflixApplication, this.appUsageAnalyticsRepositoryProvider.get());
    }
}
