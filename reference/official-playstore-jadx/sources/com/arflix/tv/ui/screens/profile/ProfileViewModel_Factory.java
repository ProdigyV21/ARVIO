package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.ProfileAvatarImageManager;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<ProfileAvatarImageManager> profileAvatarImageManagerProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<ProfileRepository> profileRepositoryProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private ProfileViewModel_Factory(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileRepository> provider3, Provider<ProfileManager> provider4, Provider<TraktRepository> provider5, Provider<WatchHistoryRepository> provider6, Provider<WatchlistRepository> provider7, Provider<IptvRepository> provider8, Provider<ProfileAvatarImageManager> provider9, Provider<CloudSyncRepository> provider10) {
        this.contextProvider = provider;
        this.authRepositoryProvider = provider2;
        this.profileRepositoryProvider = provider3;
        this.profileManagerProvider = provider4;
        this.traktRepositoryProvider = provider5;
        this.watchHistoryRepositoryProvider = provider6;
        this.watchlistRepositoryProvider = provider7;
        this.iptvRepositoryProvider = provider8;
        this.profileAvatarImageManagerProvider = provider9;
        this.cloudSyncRepositoryProvider = provider10;
    }

    public static ProfileViewModel_Factory create(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileRepository> provider3, Provider<ProfileManager> provider4, Provider<TraktRepository> provider5, Provider<WatchHistoryRepository> provider6, Provider<WatchlistRepository> provider7, Provider<IptvRepository> provider8, Provider<ProfileAvatarImageManager> provider9, Provider<CloudSyncRepository> provider10) {
        return new ProfileViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static ProfileViewModel newInstance(Context context, AuthRepository authRepository, ProfileRepository profileRepository, ProfileManager profileManager, TraktRepository traktRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, ProfileAvatarImageManager profileAvatarImageManager, CloudSyncRepository cloudSyncRepository) {
        return new ProfileViewModel(context, authRepository, profileRepository, profileManager, traktRepository, watchHistoryRepository, watchlistRepository, iptvRepository, profileAvatarImageManager, cloudSyncRepository);
    }

    @Override // javax.inject.Provider
    public ProfileViewModel get() {
        return newInstance(this.contextProvider.get(), this.authRepositoryProvider.get(), this.profileRepositoryProvider.get(), this.profileManagerProvider.get(), this.traktRepositoryProvider.get(), this.watchHistoryRepositoryProvider.get(), this.watchlistRepositoryProvider.get(), this.iptvRepositoryProvider.get(), this.profileAvatarImageManagerProvider.get(), this.cloudSyncRepositoryProvider.get());
    }
}
