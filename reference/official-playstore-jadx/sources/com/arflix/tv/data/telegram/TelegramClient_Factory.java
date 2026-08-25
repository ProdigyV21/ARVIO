package com.arflix.tv.data.telegram;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TelegramClient_Factory implements Factory<TelegramClient> {
    private final Provider<Context> contextProvider;

    private TelegramClient_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static TelegramClient_Factory create(Provider<Context> provider) {
        return new TelegramClient_Factory(provider);
    }

    public static TelegramClient newInstance(Context context) {
        return new TelegramClient(context);
    }

    @Override // javax.inject.Provider
    public TelegramClient get() {
        return newInstance(this.contextProvider.get());
    }
}
