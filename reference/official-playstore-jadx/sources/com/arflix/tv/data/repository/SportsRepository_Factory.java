package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.StreamApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SportsRepository_Factory implements Factory<SportsRepository> {
    private final Provider<StreamApi> streamApiProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;

    private SportsRepository_Factory(Provider<StreamRepository> provider, Provider<StreamApi> provider2) {
        this.streamRepositoryProvider = provider;
        this.streamApiProvider = provider2;
    }

    public static SportsRepository_Factory create(Provider<StreamRepository> provider, Provider<StreamApi> provider2) {
        return new SportsRepository_Factory(provider, provider2);
    }

    public static SportsRepository newInstance(StreamRepository streamRepository, StreamApi streamApi) {
        return new SportsRepository(streamRepository, streamApi);
    }

    @Override // javax.inject.Provider
    public SportsRepository get() {
        return newInstance(this.streamRepositoryProvider.get(), this.streamApiProvider.get());
    }
}
