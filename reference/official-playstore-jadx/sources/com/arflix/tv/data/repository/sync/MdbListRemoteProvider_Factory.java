package com.arflix.tv.data.repository.sync;

import com.arflix.tv.data.repository.MdbListRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class MdbListRemoteProvider_Factory implements Factory<MdbListRemoteProvider> {
    private final Provider<MdbListRepository> repositoryProvider;

    private MdbListRemoteProvider_Factory(Provider<MdbListRepository> provider) {
        this.repositoryProvider = provider;
    }

    public static MdbListRemoteProvider_Factory create(Provider<MdbListRepository> provider) {
        return new MdbListRemoteProvider_Factory(provider);
    }

    public static MdbListRemoteProvider newInstance(MdbListRepository mdbListRepository) {
        return new MdbListRemoteProvider(mdbListRepository);
    }

    @Override // javax.inject.Provider
    public MdbListRemoteProvider get() {
        return newInstance(this.repositoryProvider.get());
    }
}
