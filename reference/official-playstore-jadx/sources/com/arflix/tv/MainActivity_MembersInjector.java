package com.arflix.tv;

import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<ProfileRepository> profileRepositoryProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private MainActivity_MembersInjector(Provider<AuthRepository> provider, Provider<ProfileRepository> provider2, Provider<TraktRepository> provider3, Provider<ProfileManager> provider4, Provider<WatchHistoryRepository> provider5, Provider<WatchlistRepository> provider6, Provider<LauncherContinueWatchingRepository> provider7, Provider<MediaRepository> provider8, Provider<IptvRepository> provider9) {
        this.authRepositoryProvider = provider;
        this.profileRepositoryProvider = provider2;
        this.traktRepositoryProvider = provider3;
        this.profileManagerProvider = provider4;
        this.watchHistoryRepositoryProvider = provider5;
        this.watchlistRepositoryProvider = provider6;
        this.launcherContinueWatchingRepositoryProvider = provider7;
        this.mediaRepositoryProvider = provider8;
        this.iptvRepositoryProvider = provider9;
    }

    public static MembersInjector<MainActivity> create(Provider<AuthRepository> provider, Provider<ProfileRepository> provider2, Provider<TraktRepository> provider3, Provider<ProfileManager> provider4, Provider<WatchHistoryRepository> provider5, Provider<WatchlistRepository> provider6, Provider<LauncherContinueWatchingRepository> provider7, Provider<MediaRepository> provider8, Provider<IptvRepository> provider9) {
        return new MainActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static void injectAuthRepository(MainActivity mainActivity, Lazy<AuthRepository> lazy) {
        mainActivity.authRepository = lazy;
    }

    public static void injectIptvRepository(MainActivity mainActivity, Lazy<IptvRepository> lazy) {
        mainActivity.iptvRepository = lazy;
    }

    public static void injectLauncherContinueWatchingRepository(MainActivity mainActivity, Lazy<LauncherContinueWatchingRepository> lazy) {
        mainActivity.launcherContinueWatchingRepository = lazy;
    }

    public static void injectMediaRepository(MainActivity mainActivity, Lazy<MediaRepository> lazy) {
        mainActivity.mediaRepository = lazy;
    }

    public static void injectProfileManager(MainActivity mainActivity, Lazy<ProfileManager> lazy) {
        mainActivity.profileManager = lazy;
    }

    public static void injectProfileRepository(MainActivity mainActivity, Lazy<ProfileRepository> lazy) {
        mainActivity.profileRepository = lazy;
    }

    public static void injectTraktRepository(MainActivity mainActivity, Lazy<TraktRepository> lazy) {
        mainActivity.traktRepository = lazy;
    }

    public static void injectWatchHistoryRepository(MainActivity mainActivity, Lazy<WatchHistoryRepository> lazy) {
        mainActivity.watchHistoryRepository = lazy;
    }

    public static void injectWatchlistRepository(MainActivity mainActivity, Lazy<WatchlistRepository> lazy) {
        mainActivity.watchlistRepository = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MainActivity mainActivity) {
        injectAuthRepository(mainActivity, DoubleCheck.lazy((Provider) this.authRepositoryProvider));
        injectProfileRepository(mainActivity, DoubleCheck.lazy((Provider) this.profileRepositoryProvider));
        injectTraktRepository(mainActivity, DoubleCheck.lazy((Provider) this.traktRepositoryProvider));
        injectProfileManager(mainActivity, DoubleCheck.lazy((Provider) this.profileManagerProvider));
        injectWatchHistoryRepository(mainActivity, DoubleCheck.lazy((Provider) this.watchHistoryRepositoryProvider));
        injectWatchlistRepository(mainActivity, DoubleCheck.lazy((Provider) this.watchlistRepositoryProvider));
        injectLauncherContinueWatchingRepository(mainActivity, DoubleCheck.lazy((Provider) this.launcherContinueWatchingRepositoryProvider));
        injectMediaRepository(mainActivity, DoubleCheck.lazy((Provider) this.mediaRepositoryProvider));
        injectIptvRepository(mainActivity, DoubleCheck.lazy((Provider) this.iptvRepositoryProvider));
    }
}
