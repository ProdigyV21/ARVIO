package com.arflix.tv.data.telegram;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TelegramRepository_Factory implements Factory<TelegramRepository> {
    private final Provider<TelegramClient> clientProvider;
    private final Provider<Context> contextProvider;
    private final Provider<TelegramStreamingProxy> proxyProvider;

    private TelegramRepository_Factory(Provider<Context> provider, Provider<TelegramClient> provider2, Provider<TelegramStreamingProxy> provider3) {
        this.contextProvider = provider;
        this.clientProvider = provider2;
        this.proxyProvider = provider3;
    }

    public static TelegramRepository_Factory create(Provider<Context> provider, Provider<TelegramClient> provider2, Provider<TelegramStreamingProxy> provider3) {
        return new TelegramRepository_Factory(provider, provider2, provider3);
    }

    public static TelegramRepository newInstance(Context context, TelegramClient telegramClient, TelegramStreamingProxy telegramStreamingProxy) {
        return new TelegramRepository(context, telegramClient, telegramStreamingProxy);
    }

    @Override // javax.inject.Provider
    public TelegramRepository get() {
        return newInstance(this.contextProvider.get(), this.clientProvider.get(), this.proxyProvider.get());
    }
}
