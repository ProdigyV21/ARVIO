package com.arflix.tv.data.repository;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TraktApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaRepository_Factory implements Factory<MediaRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<HomeServerRepository> homeServerRepositoryProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;
    private final Provider<TmdbApi> tmdbApiProvider;
    private final Provider<TraktApi> traktApiProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;

    private MediaRepository_Factory(Provider<Context> provider, Provider<TmdbApi> provider2, Provider<TraktRepository> provider3, Provider<TraktApi> provider4, Provider<gb.h0> provider5, Provider<StreamRepository> provider6, Provider<HomeServerRepository> provider7) {
        this.contextProvider = provider;
        this.tmdbApiProvider = provider2;
        this.traktRepositoryProvider = provider3;
        this.traktApiProvider = provider4;
        this.okHttpClientProvider = provider5;
        this.streamRepositoryProvider = provider6;
        this.homeServerRepositoryProvider = provider7;
    }

    public static MediaRepository_Factory create(Provider<Context> provider, Provider<TmdbApi> provider2, Provider<TraktRepository> provider3, Provider<TraktApi> provider4, Provider<gb.h0> provider5, Provider<StreamRepository> provider6, Provider<HomeServerRepository> provider7) {
        return new MediaRepository_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MediaRepository newInstance(Context context, TmdbApi tmdbApi, TraktRepository traktRepository, TraktApi traktApi, gb.h0 h0Var, StreamRepository streamRepository, HomeServerRepository homeServerRepository) {
        return new MediaRepository(context, tmdbApi, traktRepository, traktApi, h0Var, streamRepository, homeServerRepository);
    }

    @Override // javax.inject.Provider
    public MediaRepository get() {
        return newInstance(this.contextProvider.get(), this.tmdbApiProvider.get(), this.traktRepositoryProvider.get(), this.traktApiProvider.get(), this.okHttpClientProvider.get(), this.streamRepositoryProvider.get(), this.homeServerRepositoryProvider.get());
    }
}
