package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.telegram.TelegramSourceResolver;
import com.arflix.tv.util.AnimeMapper;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamRepository_Factory implements Factory<StreamRepository> {
    private final Provider<AnimeMapper> animeMapperProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<HttpLocalScraperRuntime> httpLocalScraperRuntimeProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;
    private final Provider<StreamApi> streamApiProvider;
    private final Provider<TelegramSourceResolver> telegramSourceResolverProvider;

    private StreamRepository_Factory(Provider<Context> provider, Provider<StreamApi> provider2, Provider<gb.h0> provider3, Provider<ProfileManager> provider4, Provider<AnimeMapper> provider5, Provider<IptvRepository> provider6, Provider<HttpLocalScraperRuntime> provider7, Provider<HomeServerRepository> provider8, Provider<CloudSyncInvalidationBus> provider9, Provider<TelegramSourceResolver> provider10) {
        this.contextProvider = provider;
        this.streamApiProvider = provider2;
        this.okHttpClientProvider = provider3;
        this.profileManagerProvider = provider4;
        this.animeMapperProvider = provider5;
        this.iptvRepositoryProvider = provider6;
        this.httpLocalScraperRuntimeProvider = provider7;
        this.homeServerRepositoryProvider = provider8;
        this.invalidationBusProvider = provider9;
        this.telegramSourceResolverProvider = provider10;
    }

    public static StreamRepository_Factory create(Provider<Context> provider, Provider<StreamApi> provider2, Provider<gb.h0> provider3, Provider<ProfileManager> provider4, Provider<AnimeMapper> provider5, Provider<IptvRepository> provider6, Provider<HttpLocalScraperRuntime> provider7, Provider<HomeServerRepository> provider8, Provider<CloudSyncInvalidationBus> provider9, Provider<TelegramSourceResolver> provider10) {
        return new StreamRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static StreamRepository newInstance(Context context, StreamApi streamApi, gb.h0 h0Var, ProfileManager profileManager, AnimeMapper animeMapper, IptvRepository iptvRepository, HttpLocalScraperRuntime httpLocalScraperRuntime, HomeServerRepository homeServerRepository, CloudSyncInvalidationBus cloudSyncInvalidationBus, TelegramSourceResolver telegramSourceResolver) {
        return new StreamRepository(context, streamApi, h0Var, profileManager, animeMapper, iptvRepository, httpLocalScraperRuntime, homeServerRepository, cloudSyncInvalidationBus, telegramSourceResolver);
    }

    @Override // javax.inject.Provider
    public StreamRepository get() {
        return newInstance(this.contextProvider.get(), this.streamApiProvider.get(), this.okHttpClientProvider.get(), this.profileManagerProvider.get(), this.animeMapperProvider.get(), this.iptvRepositoryProvider.get(), this.httpLocalScraperRuntimeProvider.get(), this.homeServerRepositoryProvider.get(), this.invalidationBusProvider.get(), this.telegramSourceResolverProvider.get());
    }
}
