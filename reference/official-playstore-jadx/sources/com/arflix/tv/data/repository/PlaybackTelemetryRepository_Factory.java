package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class PlaybackTelemetryRepository_Factory implements Factory<PlaybackTelemetryRepository> {
    private final Provider<Context> contextProvider;

    private PlaybackTelemetryRepository_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static PlaybackTelemetryRepository_Factory create(Provider<Context> provider) {
        return new PlaybackTelemetryRepository_Factory(provider);
    }

    public static PlaybackTelemetryRepository newInstance(Context context) {
        return new PlaybackTelemetryRepository(context);
    }

    @Override // javax.inject.Provider
    public PlaybackTelemetryRepository get() {
        return newInstance(this.contextProvider.get());
    }
}
