package com.arflix.tv.util;

import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class AnimeMapper_Factory implements Factory<AnimeMapper> {
    private final Provider<StreamApi> streamApiProvider;
    private final Provider<TmdbApi> tmdbApiProvider;

    private AnimeMapper_Factory(Provider<StreamApi> provider, Provider<TmdbApi> provider2) {
        this.streamApiProvider = provider;
        this.tmdbApiProvider = provider2;
    }

    public static AnimeMapper_Factory create(Provider<StreamApi> provider, Provider<TmdbApi> provider2) {
        return new AnimeMapper_Factory(provider, provider2);
    }

    public static AnimeMapper newInstance(StreamApi streamApi, TmdbApi tmdbApi) {
        return new AnimeMapper(streamApi, tmdbApi);
    }

    @Override // javax.inject.Provider
    public AnimeMapper get() {
        return newInstance(this.streamApiProvider.get(), this.tmdbApiProvider.get());
    }
}
