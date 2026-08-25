package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.repository.TraktRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TraktRemoteProvider_Factory implements Factory<TraktRemoteProvider> {
    private final Provider<TraktRepository> traktRepositoryProvider;

    private TraktRemoteProvider_Factory(Provider<TraktRepository> provider) {
        this.traktRepositoryProvider = provider;
    }

    public static TraktRemoteProvider_Factory create(Provider<TraktRepository> provider) {
        return new TraktRemoteProvider_Factory(provider);
    }

    public static TraktRemoteProvider newInstance(TraktRepository traktRepository) {
        return new TraktRemoteProvider(traktRepository);
    }

    @Override // javax.inject.Provider
    public TraktRemoteProvider get() {
        return newInstance(this.traktRepositoryProvider.get());
    }
}
