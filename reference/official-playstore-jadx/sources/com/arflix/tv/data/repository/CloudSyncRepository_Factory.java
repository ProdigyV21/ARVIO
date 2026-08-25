package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.local.PluginDataStore;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class CloudSyncRepository_Factory implements Factory<CloudSyncRepository> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CatalogRepository> catalogRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<PluginDataStore> pluginDataStoreProvider;
    private final Provider<ProfileAvatarImageManager> profileAvatarImageManagerProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<ProfileRepository> profileRepositoryProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<SyncProviderStore> syncProviderStoreProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;
    private final Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
    private final Provider<WatchlistRepository> watchlistRepositoryProvider;

    private CloudSyncRepository_Factory(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileRepository> provider3, Provider<ProfileManager> provider4, Provider<CatalogRepository> provider5, Provider<IptvRepository> provider6, Provider<StreamRepository> provider7, Provider<HomeServerRepository> provider8, Provider<TraktRepository> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<ProfileAvatarImageManager> provider12, Provider<CloudSyncInvalidationBus> provider13, Provider<PluginDataStore> provider14, Provider<SyncProviderStore> provider15) {
        this.contextProvider = provider;
        this.authRepositoryProvider = provider2;
        this.profileRepositoryProvider = provider3;
        this.profileManagerProvider = provider4;
        this.catalogRepositoryProvider = provider5;
        this.iptvRepositoryProvider = provider6;
        this.streamRepositoryProvider = provider7;
        this.homeServerRepositoryProvider = provider8;
        this.traktRepositoryProvider = provider9;
        this.watchHistoryRepositoryProvider = provider10;
        this.watchlistRepositoryProvider = provider11;
        this.profileAvatarImageManagerProvider = provider12;
        this.invalidationBusProvider = provider13;
        this.pluginDataStoreProvider = provider14;
        this.syncProviderStoreProvider = provider15;
    }

    public static CloudSyncRepository_Factory create(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileRepository> provider3, Provider<ProfileManager> provider4, Provider<CatalogRepository> provider5, Provider<IptvRepository> provider6, Provider<StreamRepository> provider7, Provider<HomeServerRepository> provider8, Provider<TraktRepository> provider9, Provider<WatchHistoryRepository> provider10, Provider<WatchlistRepository> provider11, Provider<ProfileAvatarImageManager> provider12, Provider<CloudSyncInvalidationBus> provider13, Provider<PluginDataStore> provider14, Provider<SyncProviderStore> provider15) {
        return new CloudSyncRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    public static CloudSyncRepository newInstance(Context context, AuthRepository authRepository, ProfileRepository profileRepository, ProfileManager profileManager, CatalogRepository catalogRepository, IptvRepository iptvRepository, StreamRepository streamRepository, HomeServerRepository homeServerRepository, TraktRepository traktRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, ProfileAvatarImageManager profileAvatarImageManager, CloudSyncInvalidationBus cloudSyncInvalidationBus, PluginDataStore pluginDataStore, SyncProviderStore syncProviderStore) {
        return new CloudSyncRepository(context, authRepository, profileRepository, profileManager, catalogRepository, iptvRepository, streamRepository, homeServerRepository, traktRepository, watchHistoryRepository, watchlistRepository, profileAvatarImageManager, cloudSyncInvalidationBus, pluginDataStore, syncProviderStore);
    }

    @Override // javax.inject.Provider
    public CloudSyncRepository get() {
        return newInstance(this.contextProvider.get(), this.authRepositoryProvider.get(), this.profileRepositoryProvider.get(), this.profileManagerProvider.get(), this.catalogRepositoryProvider.get(), this.iptvRepositoryProvider.get(), this.streamRepositoryProvider.get(), this.homeServerRepositoryProvider.get(), this.traktRepositoryProvider.get(), this.watchHistoryRepositoryProvider.get(), this.watchlistRepositoryProvider.get(), this.profileAvatarImageManagerProvider.get(), this.invalidationBusProvider.get(), this.pluginDataStoreProvider.get(), this.syncProviderStoreProvider.get());
    }
}
