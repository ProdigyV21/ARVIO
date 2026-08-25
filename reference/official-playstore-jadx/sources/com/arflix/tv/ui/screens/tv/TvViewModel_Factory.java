package com.arflix.tv.ui.screens.tv;

import android.content.Context;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.IptvRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class TvViewModel_Factory implements Factory<TvViewModel> {
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<IptvRepository> iptvRepositoryProvider;

    private TvViewModel_Factory(Provider<Context> provider, Provider<IptvRepository> provider2, Provider<CloudSyncRepository> provider3) {
        this.contextProvider = provider;
        this.iptvRepositoryProvider = provider2;
        this.cloudSyncRepositoryProvider = provider3;
    }

    public static TvViewModel_Factory create(Provider<Context> provider, Provider<IptvRepository> provider2, Provider<CloudSyncRepository> provider3) {
        return new TvViewModel_Factory(provider, provider2, provider3);
    }

    public static TvViewModel newInstance(Context context, IptvRepository iptvRepository, CloudSyncRepository cloudSyncRepository) {
        return new TvViewModel(context, iptvRepository, cloudSyncRepository);
    }

    @Override // javax.inject.Provider
    public TvViewModel get() {
        return newInstance(this.contextProvider.get(), this.iptvRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get());
    }
}
