package com.arflix.tv.data.telegram;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TelegramStreamingProxy_Factory implements Factory<TelegramStreamingProxy> {
    private final Provider<TelegramClient> clientProvider;
    private final Provider<Context> contextProvider;

    private TelegramStreamingProxy_Factory(Provider<Context> provider, Provider<TelegramClient> provider2) {
        this.contextProvider = provider;
        this.clientProvider = provider2;
    }

    public static TelegramStreamingProxy_Factory create(Provider<Context> provider, Provider<TelegramClient> provider2) {
        return new TelegramStreamingProxy_Factory(provider, provider2);
    }

    public static TelegramStreamingProxy newInstance(Context context, TelegramClient telegramClient) {
        return new TelegramStreamingProxy(context, telegramClient);
    }

    @Override // javax.inject.Provider
    public TelegramStreamingProxy get() {
        return newInstance(this.contextProvider.get(), this.clientProvider.get());
    }
}
