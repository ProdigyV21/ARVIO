package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class LauncherContinueWatchingRepository_Factory implements Factory<LauncherContinueWatchingRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<RemoteSyncManager> remoteSyncManagerProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;

    private LauncherContinueWatchingRepository_Factory(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktRepository> provider3, Provider<RemoteSyncManager> provider4, Provider<WatchHistoryRepository> provider5, Provider<StreamRepository> provider6) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
        this.traktRepositoryProvider = provider3;
        this.remoteSyncManagerProvider = provider4;
        this.watchHistoryRepositoryProvider = provider5;
        this.streamRepositoryProvider = provider6;
    }

    public static LauncherContinueWatchingRepository_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2, Provider<TraktRepository> provider3, Provider<RemoteSyncManager> provider4, Provider<WatchHistoryRepository> provider5, Provider<StreamRepository> provider6) {
        return new LauncherContinueWatchingRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static LauncherContinueWatchingRepository newInstance(Context context, ProfileManager profileManager, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, WatchHistoryRepository watchHistoryRepository, StreamRepository streamRepository) {
        return new LauncherContinueWatchingRepository(context, profileManager, traktRepository, remoteSyncManager, watchHistoryRepository, streamRepository);
    }

    @Override // javax.inject.Provider
    public LauncherContinueWatchingRepository get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get(), this.traktRepositoryProvider.get(), this.remoteSyncManagerProvider.get(), this.watchHistoryRepositoryProvider.get(), this.streamRepositoryProvider.get());
    }
}
