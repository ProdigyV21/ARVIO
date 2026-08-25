package com.arflix.tv.data.telegram;

import android.content.Context;
import com.arflix.tv.data.api.TmdbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TelegramSourceResolver_Factory implements Factory<TelegramSourceResolver> {
    private final Provider<Context> contextProvider;
    private final Provider<TelegramSearchMatcher> matcherProvider;
    private final Provider<TelegramRepository> repositoryProvider;
    private final Provider<TmdbApi> tmdbApiProvider;

    private TelegramSourceResolver_Factory(Provider<TelegramRepository> provider, Provider<TelegramSearchMatcher> provider2, Provider<TmdbApi> provider3, Provider<Context> provider4) {
        this.repositoryProvider = provider;
        this.matcherProvider = provider2;
        this.tmdbApiProvider = provider3;
        this.contextProvider = provider4;
    }

    public static TelegramSourceResolver_Factory create(Provider<TelegramRepository> provider, Provider<TelegramSearchMatcher> provider2, Provider<TmdbApi> provider3, Provider<Context> provider4) {
        return new TelegramSourceResolver_Factory(provider, provider2, provider3, provider4);
    }

    public static TelegramSourceResolver newInstance(TelegramRepository telegramRepository, TelegramSearchMatcher telegramSearchMatcher, TmdbApi tmdbApi, Context context) {
        return new TelegramSourceResolver(telegramRepository, telegramSearchMatcher, tmdbApi, context);
    }

    @Override // javax.inject.Provider
    public TelegramSourceResolver get() {
        return newInstance(this.repositoryProvider.get(), this.matcherProvider.get(), this.tmdbApiProvider.get(), this.contextProvider.get());
    }
}
